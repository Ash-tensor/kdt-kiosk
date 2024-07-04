package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/admin/menu")
@Controller
public class MenuAddController {
    private final MenuService menuService;

    @GetMapping("/add")
    public String viewAddMenu(){return "admin/menu/add";}

    @PostMapping("/add")
    public void addMenu(String menuDescription, MultipartFile file,
                          String menuName, int categoryID, int basePrice, Model model) throws IOException {
        menuService.addMenu(menuDescription, file, menuName, categoryID, basePrice);
        model.addAttribute("menuName", menuName);

    }


}
