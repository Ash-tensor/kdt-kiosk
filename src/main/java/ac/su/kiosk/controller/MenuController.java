package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/all")
    public List<Menu> getAllMenu(Model model) {
//        List<Menu> menuList = menuService.getAll();
        return menuService.getAll();
    }
    // 모든 메뉴를 검색해서 반환 ( JSON 데이터 형식 )

//    @GetMapping("/{categoryName}")
//    public List<Menu> getAllMenuByCategory(@PathVariable String categoryName) {
//        Optional<Category> categoryOpt = menuService.getCategoryByName(categoryName);
//        if (categoryOpt.isPresent()) {
//            Category category = categoryOpt.get();
//            return menuService.getAllByCategory(category);
//        } else {
//            throw new RuntimeException("Category not found");
//        }
//    } // 검색된 카테고리가 존재할 시, 그 카테고리로 메뉴를 검색해서 반환 ( JSON 데이터 )

    @GetMapping("/{categoryId}")   // 카테고리 ID로 찾는 코드
    public List<Menu> getMenusByCategory(@PathVariable Long categoryId) {
        return menuService.getMenusByCategory(categoryId);
    }
}
