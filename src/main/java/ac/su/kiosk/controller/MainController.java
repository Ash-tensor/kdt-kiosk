package ac.su.kiosk.controller;

import ac.su.kiosk.service.CategoryService;
import ac.su.kiosk.service.MenuService;
import ac.su.kiosk.service.CustomOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CustomOptionService customOptionService;

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

//    @GetMapping("/category")
//    public String categoryPage(@RequestParam Long id, Model model) {
//        model.addAttribute("category", categoryService.getCategory(id));
//        model.addAttribute("menus", menuService.getMenusByCategory(id));
//        return "category";
//    }
//
//    @GetMapping("/menu")
//    public String menuPage(@RequestParam Long id, Model model) {
//        model.addAttribute("menu", menuService.getMenu(id));
//        model.addAttribute("customOptions", customOptionService.getCustomOptionsByMenu(id));
//        return "menu";
//    }
//
//    @GetMapping("/custom-options")
//    public String customOptionsPage(@RequestParam Long id, Model model) {
//        model.addAttribute("customOptions", customOptionService.getCustomOptionsByMenu(id));
//        return "customOptions";
//    }
}
