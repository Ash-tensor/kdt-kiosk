package ac.su.kiosk.controller;

import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/menu")
@Controller
public class MenuAddController {
    private final MenuService menuService;

    @GetMapping("/add")
    public String addMenu() {
        menuService.addMenu();


    }


}
