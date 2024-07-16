package ac.su.kiosk.controller;

import ac.su.kiosk.domain.*;
import ac.su.kiosk.repository.MenuRepository;
import ac.su.kiosk.repository.OrderModuleDTORepository;
import ac.su.kiosk.service.OrderService;
import ac.su.kiosk.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
@RestController
public class GPTController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final OrderModuleDTORepository orderModuleDTORepository;
    private final MenuRepository menuRepository;

    private final AtomicBoolean isCheckoutTriggered = new AtomicBoolean(false);

    // 외부에서 GPT가 주문하는 API ENDPOINT
    @GetMapping("/trigger-checkout")
    public boolean triggerCheckout(@RequestParam String type, @RequestParam String cupSize,
                                  @RequestParam int quantity, @RequestParam long price) {
        isCheckoutTriggered.set(true);
        // 실제 체크아웃 로직을 호출할 수 있음
//        {타입: 아이스 아메리카노, 온도: 차갑게, 크기: 라지, 수량: 1, 가격: 4,000원}

        OrderModuleDTO gptOrderModuleDTO = paymentService.gptOrderModuleDTO(price);
        orderModuleDTORepository.save(gptOrderModuleDTO);
//        Menu menu = menuRepository.findByName(type).get();

        return true;
    }

    // Checkout 상태 확인
    @GetMapping("/checkout-status")
    public Boolean checkCheckoutStatus() {
        boolean triggered = isCheckoutTriggered.get();
        if (triggered) {
            isCheckoutTriggered.set(false); // 상태 초기화
            return true;
        }
        return false;
    }

    @GetMapping("/get-remote-order")
    public OrderModuleDTO getRemoteOrder() {
        return orderModuleDTORepository.findByGpt(true).get(0);
    }
}
