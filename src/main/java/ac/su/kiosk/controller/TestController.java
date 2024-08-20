package ac.su.kiosk.controller;

import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;
import ac.su.kiosk.service.PaymentService;
import ac.su.kiosk.service.StorageService;
import ac.su.kiosk.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;
    private final TestRepo testRepo;
    private final PaymentService paymentService;
    private final StorageService storageService;
    @PostMapping("/upload")
    public String imagePostTest(MultipartFile file, Model model) throws IOException {
        String message = testService.uploadFile(file);
        TestEntity test = new TestEntity();
        test.setTestString(message);
        testRepo.save(test);
        model.addAttribute("message", message);
        return "/test/upload_result";
    }
    @GetMapping("/upload_test")
    public ResponseEntity<String> restImageUrlTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload_test")
    public ResponseEntity<String> restImage(@RequestPart("file") MultipartFile file) throws IOException {
        try {
            String message = storageService.uploadFile(file);
            TestEntity test = new TestEntity();
            test.setTestString(message);
            testRepo.save(test);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


