package ac.su.kiosk.controller;

import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;
import ac.su.kiosk.service.PaymentService;
import ac.su.kiosk.service.TestService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;
    private final TestRepo testRepo;
    private final PaymentService paymentService;

    @GetMapping("/upload")
    public String uploadTest() {
        return "test/upload_test";
    }

    @PostMapping("/upload")
    public String imagePostTest(MultipartFile file, Model model) throws IOException {
        String message = testService.uploadFile(file);
        TestEntity test = new TestEntity();
        test.setTestString(message);
        testRepo.save(test);
        model.addAttribute("message", message);
        return "/test/upload_result";
    }

    @GetMapping("/payment")
    public String paymentPage(Model model) {
        OrderModuleDTO requestDto = testService.makeOrderModuleDTO();
        model.addAttribute("requestDto", requestDto);
        return "test/payment";
    }

    @ResponseBody
    @PostMapping("/payment")
    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody OrderModuleDTO request) {
        //OrderModuleDTO request =  testService.makeOrderModuleDTO();
        IamportResponse<Payment> IamportResponse = paymentService.paymentByCallback(request);

        log.info("결제 응답 = {}", IamportResponse.getResponse().toString());
        return new ResponseEntity<>(IamportResponse, HttpStatus.OK);
    }
}
