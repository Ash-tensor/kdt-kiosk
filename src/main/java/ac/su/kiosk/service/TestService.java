package ac.su.kiosk.service;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
}
