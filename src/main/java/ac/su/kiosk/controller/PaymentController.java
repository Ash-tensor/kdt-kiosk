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
//@Controller
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseEntity<OrderModuleDTO> getPaymentInfo() {
        OrderModuleDTO requestDto = paymentService.makeOrderModuleDTO();
        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<IamportResponse<Payment>> validatePayment(@RequestBody OrderModuleDTO request) {
        IamportResponse<Payment> iamportResponse = paymentService.paymentByCallback(request);
        log.info("결제 응답 = {}", iamportResponse.getResponse().toString());
        return new ResponseEntity<>(iamportResponse, HttpStatus.OK);
    }

//    @GetMapping("/payment")
//    public String paymentPage(Model model) {
//        OrderModuleDTO requestDto = paymentService.makeOrderModuleDTO();
//        model.addAttribute("requestDto", requestDto);
//        return "test/payment";
//    }

//    @ResponseBody
//    @PostMapping("/payment")
//    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody OrderModuleDTO request) {
//        //OrderModuleDTO request =  testService.makeOrderModuleDTO();
//        IamportResponse<Payment> IamportResponse = paymentService.paymentByCallback(request);
//        log.info("결제 응답 = {}", IamportResponse.getResponse().toString());
//        return new ResponseEntity<>(IamportResponse, HttpStatus.OK);
//    }
}