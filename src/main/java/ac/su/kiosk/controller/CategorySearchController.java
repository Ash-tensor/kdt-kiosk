package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.StoreCategoryVisibility;
import ac.su.kiosk.service.CategoryService;
import ac.su.kiosk.service.StoreCategoryVisibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategorySearchController {

    private final CategoryService categoryService;
    private final StoreCategoryVisibilityService visibilityService;


    // 전체 카테고리를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 매장 ID로 카테고리를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/store/{storeId}")
    public List<Category> getCategoriesByStore(@PathVariable Long storeId) {
        List<StoreCategoryVisibility> visibilityList = visibilityService.getVisibilityByStore(storeId);
        return visibilityList.stream()
                .filter(StoreCategoryVisibility::isVisible)
                .map(StoreCategoryVisibility::getCategory)
                .collect(Collectors.toList());
    }
}
