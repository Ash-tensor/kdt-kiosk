package ac.su.kiosk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/menu")
public class MenuAddController {
    @PostMapping("/add")
    public String addMenu() {
        return "addMenu";
    }

}
