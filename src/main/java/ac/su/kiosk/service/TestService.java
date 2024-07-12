package ac.su.kiosk.service;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepo testRepo;
    private final Storage storage;
    @Value("${spring.cloud.gcp.storage.bucket-name}")
    private String bucketName;
    private final PaymentService paymentService;

    public String test(Long id) {
        Optional<TestEntity> temp = testRepo.findById(id);
        TestEntity temp2 = temp.get();
        return temp2.testString;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            String blobName = file.getOriginalFilename();
            BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, blobName).build(),
                file.getBytes()
        );
        return "성공" + blobInfo.getMediaLink();
        } catch (IOException e) {
            return "실패";
        }
    }

    public OrderModuleDTO makeOrderModuleDTO() {
        OrderModuleDTO orderModuleDTO = new OrderModuleDTO();
        orderModuleDTO.setStatus(PaymentStatus.READY);
        orderModuleDTO.setEmail("test@example.com");
        orderModuleDTO.setAddress("서울특별시 노원구 화랑로");
        orderModuleDTO.setStoreName("5조");
        orderModuleDTO.setOrderUid(UUID.randomUUID().toString());
        orderModuleDTO.setPrice(100L);

        return orderModuleDTO;
    }

    public void JavaNativePayment() {
        OrderModuleDTO orderModuleDTO = makeOrderModuleDTO();
        String IMP_API_URL = "https://api.iamport.kr/payments/prepare";
        String MERCHANT_ID = "imp55148327";

        try {
            // 결제 요청
            String response = requestPayment("orderUid123", "Test Item",
                    1000, "BuyerName", "buyer@example.com",
                    "123 Street, City");

            // 결제 응답 처리
            processPaymentResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestPayment(String orderUid, String itemName, int paymentPrice,
                                 String buyerName, String buyerEmail, String buyerAddress) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String IMP_API_URL = "https://api.iamport.kr/payments/prepare";
        HttpPost httpPost = new HttpPost(IMP_API_URL);

        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("pg", "html5_inicis.INIpayTest");
        paymentData.put("pay_method", "card");
        paymentData.put("merchant_uid", orderUid);
        paymentData.put("name", itemName);
        paymentData.put("amount", paymentPrice);
        paymentData.put("buyer_email", buyerEmail);
        paymentData.put("buyer_name", buyerName);
        paymentData.put("buyer_tel", "010-1234-5678");
        paymentData.put("buyer_addr", buyerAddress);
        paymentData.put("buyer_postcode", "123-456");

        Gson gson = new Gson();
        String jsonData = gson.toJson(paymentData);

        StringEntity entity = new StringEntity(jsonData);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");

        // HTTP 요청 실행
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    public void processPaymentResponse(String response) {
        Gson gson = new Gson();
        Map<String, Object> responseData = gson.fromJson(response, Map.class);

        if ((boolean) responseData.get("success")) {
            // 결제 성공 처리 로직
            System.out.println("Payment Success: " + responseData);
        } else {
            // 결제 실패 처리 로직
            System.out.println("Payment Failed: " + responseData);
        }
    }




}
