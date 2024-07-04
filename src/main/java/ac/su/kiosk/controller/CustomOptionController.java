package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.service.CustomOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomOptionController {

    @Autowired
    private CustomOptionService customOptionService;

    @GetMapping("/customOption")
    public String customOptionPage(@RequestParam("menuId") Long id, Model model) {
        Optional<CustomOption> options = customOptionService.getCustomOptionsByMenu(id);
        model.addAttribute("options", options);
        return "customOption";
    }
}
