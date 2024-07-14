package ac.su.kiosk.controller;

import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/menu")
public class MenuDeleteController {
    private final MenuService menuService;

    @DeleteMapping("/delete/{id}")
    public void deleteMenu(@PathVariable int id) {
        menuService.deleteMenu(id);
    }
}
