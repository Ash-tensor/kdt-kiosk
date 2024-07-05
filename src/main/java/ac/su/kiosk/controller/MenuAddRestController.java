package ac.su.kiosk.controller;

import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/menu")
public class MenuAddRestController {
    private final MenuService menuService;

    @PostMapping("/add_rest")
    public ResponseEntity<String> addMenu(@RequestParam("description") String description,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("name") String name,
                          @RequestParam("category") int category,
                          @RequestParam("price") int price) throws IOException {
        String message = menuService.addMenu(description, file, name, category, price);
        return ResponseEntity.ok("성공");
    }
}
