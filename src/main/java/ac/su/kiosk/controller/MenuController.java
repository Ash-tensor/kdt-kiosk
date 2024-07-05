package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

//    @GetMapping("/menu")
//    public String menuPage(@RequestParam("categoryId") Long id, Model model) {
//        Optional<Menu> menus = menuService.getMenusByCategory(id);
//        model.addAttribute("menus", menus);
//        return "menu";
//    }
}
