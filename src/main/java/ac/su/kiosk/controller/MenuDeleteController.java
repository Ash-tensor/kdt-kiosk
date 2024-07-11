package ac.su.kiosk.controller;

import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuDeleteController {
    private final MenuService menuService;

    @DeleteMapping("/admin/menu/delete")
    public void deleteMenu(@RequestParam int id) {
        menuService.deleteMenu(id);
    }
}
