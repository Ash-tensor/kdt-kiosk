package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MenuSearchController {

    private final MenuService menuService;

    // 모든 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/all")
    public List<Menu> getAllMenu() {
        return menuService.getAll();
    }

    // 카테고리 ID로 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/{categoryId}")
    public List<Menu> getMenusByCategory(@PathVariable Long categoryId) {
        return menuService.getMenusByCategory(categoryId);
    }
}
