package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.CategoryRepository;
import ac.su.kiosk.service.CategoryService;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/menu")
@Controller
public class MenuAddController {
    private final MenuService menuService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String viewAddMenu(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/menu/add";
    }

    @PostMapping("/add")
    public String addMenu(@RequestParam("description") String description,
                        @RequestParam("file") MultipartFile file,
                        @RequestParam("name") String name,
                        @RequestParam("category") int category,
                        @RequestParam("price") int price, Model model) throws IOException {
        String message = menuService.addMenu(description, file, name, category, price);
        model.addAttribute("menuName", name);
        model.addAttribute("message", message);
        return "admin/menu/add_result";

    }
}
