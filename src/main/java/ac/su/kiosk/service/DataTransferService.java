package ac.su.kiosk.service;

import ac.su.kiosk.dto.OrderTestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

// 받은 주문에 관한 json파일을 다시 파이썬으로 보내기 위한 서비스
@Service
public class DataTransferService {
    private final WebClient webClient;

    public DataTransferService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:5000") // Python 서버의 주소
                .build();
    }

    public void sendData(OrderTestDTO order) {
        // price 값만을 JSON 형태로 포장
        Map<String, Long> priceMap = Collections.singletonMap("price", order.getPrice());

        webClient.post()
                .uri("/receive_data")
                .bodyValue(priceMap)  // 객체 대신 Map 전달
//                .body(Mono.just(order), OrderTestDTO.class) // OrderTestDTO 데이터를 다보내는 것
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }
}
