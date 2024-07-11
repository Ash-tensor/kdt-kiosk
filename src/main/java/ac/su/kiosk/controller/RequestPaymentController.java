package ac.su.kiosk.controller;

import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.service.PaymentService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController("/api/request_payment")
public class RequestPaymentController {
    private final PaymentService paymentService;

    @GetMapping("/check_out")
    public OrderModuleDTO paymentPage(Model model) {
        return paymentService.makeOrderModuleDTO();
    }

    @ResponseBody
    @PostMapping("/check_out")
    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody OrderModuleDTO request) {
        IamportResponse<Payment> iamportResponse = paymentService.paymentByCallback(request);
        log.info("결제 응답 = {}", iamportResponse.getResponse().toString());
        return new ResponseEntity<>(iamportResponse, HttpStatus.OK);
    }
}
