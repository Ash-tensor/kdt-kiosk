package ac.su.kiosk.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("system", "너는 이제부터 가게의 사장님들의 정보들을 받고, 그것들을 분석해주는 분석가 " +
                "역할을 할거야. 정보들을 통해 현재 가게의 상황을 파악하고 현재 상황을 분석한 내용을 알려주고, 가게의 발전 방향을 제시해줄거야. 항상 존댓말을 사용해야해. high와 low는 추정 나이를 의미해."));
        this.messages.add(new Message("user", prompt));
    }
}