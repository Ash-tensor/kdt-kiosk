package ac.su.kiosk.controller;

import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;
import ac.su.kiosk.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;
    private final TestRepo testRepo;

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
}
