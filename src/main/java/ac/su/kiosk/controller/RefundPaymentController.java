package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.IAmPortProperty;
import ac.su.kiosk.dto.OrderRefundDTO;
import ac.su.kiosk.service.OrderCompleteService;
import ac.su.kiosk.service.OrderItemService;
import ac.su.kiosk.service.OrderService;
import ac.su.kiosk.service.RefundPaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/payment")
public class RefundPaymentController {
    private final RefundPaymentService refundPaymentService;
    private final OrderItemService orderItemService;
    private final OrderCompleteService orderCompleteService;
    private final IAmPortProperty iAmPortProperty;

    @GetMapping("/all")
    public List<OrderRefundDTO> getAllOrders(){
        return refundPaymentService.getAllOrders();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam(value = "id", required = false) Long id) {

        // ash: fun refund << 환불에 성공하면 true를 반환하는 로직
        if(refund(refundPaymentService.getPaymentUid(id))) {
            // 환불이 true이면 오더 id에 따라 orderItem부터 모두 삭제해야함
            // ash: 24.07.19 테스트 완료

            orderItemService.getOrderItem(id).forEach(orderItem -> {
                orderItemService.deleteOrderItem(orderItem.getId());
            });
            
            // 추가로 completeOrder도 삭제해야함
            orderCompleteService.deleteOrderComplete(id);


            refundPaymentService.deleteOrder(id);
            return ResponseEntity.ok("삭제됨");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public String getToken(){
        try {
            // HttpClient 생성
            HttpClient client = HttpClient.newHttpClient();

            // 요청 바디 작성
            String jsonInputString = "{\"imp_key\": \""+iAmPortProperty.getApiKey()+"\", \"imp_secret\": \""+iAmPortProperty.getSecretKey()+"\"}";

            // HttpRequest 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.iamport.kr/users/getToken"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            // 요청 보내고 응답 받기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답 코드 및 바디 출력
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            JSONObject responseBody = new JSONObject(response.body());
            JSONObject responseObject = responseBody.getJSONObject("response");
            String accessToken = responseObject.getString("access_token");
            System.out.println("Access Token: " + accessToken);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean refund(String refundCode){
        String token = getToken();
        try {
            // HttpClient 생성
            HttpClient client = HttpClient.newHttpClient();

            // 요청 바디 작성
            String jsonInputString = "{\"imp_uid\": \"" + refundCode + "\"}";

            // HttpRequest 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.iamport.kr/payments/cancel?_token="+token))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            // 요청 보내고 응답 받기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답 코드 및 바디 출력
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
