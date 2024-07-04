package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.service.CustomOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomOptionController {

    @Autowired
    private CustomOptionService customOptionService;

    @GetMapping("/custom-options")
    public Optional<CustomOption> getCustomOptionsByMenu(@RequestParam Long id) {
        return customOptionService.getCustomOptionsByMenu(id);
    }
}
