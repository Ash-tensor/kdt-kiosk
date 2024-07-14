package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.CategoryService;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuSearchController {

    private final MenuService menuService;
    private final CategoryService categoryService;

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

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
