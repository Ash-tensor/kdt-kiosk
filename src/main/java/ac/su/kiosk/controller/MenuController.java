package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/all")
    public String getAllMenu(Model model) {
        List<Menu> menuList = menuService.getAll();
        model.addAttribute("menuList", menuList);
        return "menuList"; // 반환할 뷰의 이름
    }

    @GetMapping("/{categoryName}")
    public String getAllMenuByCategory(@PathVariable String categoryName, Model model) {
        Optional<Category> categoryOpt = menuService.getCategoryByName(categoryName);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            List<Menu> menuList = menuService.getAllByCategory(category);
            model.addAttribute("menuList", menuList);
            return "menuList"; // 반환할 뷰의 이름
        } else {
            model.addAttribute("error", "Category not found");
            return "error"; // 에러 뷰의 이름
        }
    }
}
