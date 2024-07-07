package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus/")
public class CustomOptionController {

    @Autowired
    private CustomOptionService customOptionService;

    @Autowired
    private MenuService menuService;

    @GetMapping("select-custom-option/{menuId}")
    public List<CustomOption> getCustomOptionsByMenuId(@PathVariable Long menuId ){
        return customOptionService.getCustomOptionsByMenu(menuId);
    }
}
