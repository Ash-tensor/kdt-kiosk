package ac.su.kiosk.controller;

import ac.su.kiosk.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final TestService testService;
    @GetMapping("/")
    public String testpage() {
        String temp = testService.test(1l);
        return temp;
    }

}
