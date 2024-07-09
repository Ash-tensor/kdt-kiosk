package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus/")
public class CustomOptionController {

    private final CustomOptionService customOptionService;

    private final MenuService menuService;

    @GetMapping("select-custom-option/{menuId}")
    public List<CustomOption> getCustomOptionsByMenuId(@PathVariable int menuId){
        return customOptionService.getCustomOptionsByMenu(menuId);
    }
}
