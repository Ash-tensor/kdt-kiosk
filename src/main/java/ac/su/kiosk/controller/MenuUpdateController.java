package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.dto.MenuDTO;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/update/menus")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MenuUpdateController {

    private final MenuService menuService;

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable int id, @RequestBody MenuDTO menuDto) {
        return menuService.updateMenu(id, menuDto);
    }

    // 이미 MenuSearchController 존재.
//    @GetMapping("/list")
//    public List<Menu> getAllMenu() {
//        return menuUpdateService.getAll();
//    }
//
//    @GetMapping("/category/{categoryId}")
//    public List<Menu> getMenusByCategory(@PathVariable Long categoryId) {
//        return menuUpdateService.getMenusByCategory(categoryId);

//    }
    // 이미 MenuAddRestController 존재.
//    @PostMapping("/add")
//    public Menu addMenu(@RequestBody Menu menu) {
//        return menuUpdateService.addMenu(menu);

//    }

    // 이미 MenuDeleteController 존재.
//    @DeleteMapping("/{id}")
//    public void deleteMenu(@PathVariable int id) {
//        menuUpdateService.deleteMenu(id);
//    }
}
