package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/update/menus")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MenuUpdateController {

    private final MenuUpdateService menuUpdateService;

    @GetMapping("/list")
    public List<Menu> getAllMenu() {
        return menuUpdateService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Menu> getMenusByCategory(@PathVariable Long categoryId) {
        return menuUpdateService.getMenusByCategory(categoryId);
    }

    @PostMapping("/add")
    public Menu addMenu(@RequestBody Menu menu) {
        return menuUpdateService.addMenu(menu);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable int id, @RequestBody Menu menu) {
        return menuUpdateService.updateMenu(id, menu);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable int id) {
        menuUpdateService.deleteMenu(id);
    }
}
