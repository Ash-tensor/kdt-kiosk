package ac.su.kiosk.controller;

import ac.su.kiosk.dto.ChatGPTResponse;
import ac.su.kiosk.dto.ChatGPTRequest;

import ac.su.kiosk.dto.Message;
import ac.su.kiosk.dto.OrderStatisticDTO;
import ac.su.kiosk.service.OrderStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/bot")
public class CustomBotController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @Autowired
    private OrderStatisticService orderStatisticService;

//    @GetMapping("/chat")
//    public String chat(@RequestParam(name = "prompt")String prompt){
//        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
//        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
//        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
//    }

    @GetMapping("/analysis")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        // OrderStatisticDTO 리스트를 가져옴
        List<OrderStatisticDTO> orderStatistics = orderStatisticService.getOrderItemsWithHumanRekognition();

        // ChatGPTRequest 생성
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);

        // OrderStatisticDTO 데이터를 메시지 형식으로 추가
        for (OrderStatisticDTO order : orderStatistics) {
            String messageContent = String.format("Order ID: %d, Price: %d, Menu ID: %d, Gender: %s, High: %d, Low: %d, Average: %.2f",
                    order.getOrderid(), order.getPrice(), order.getMenuid(),
                    order.isGender() ? "Male" : "Female", order.getHigh(), order.getLow(), order.getAverageHighLow());
            request.getMessages().add(new Message("user", messageContent));
        }

        // ChatGPT API 호출
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        // 응답에서 내용 반환
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }


}