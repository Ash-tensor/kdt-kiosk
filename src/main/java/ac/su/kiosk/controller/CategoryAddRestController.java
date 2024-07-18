package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/category")
@RestController
public class CategoryAddRestController {
    private final CategoryService categoryService;

    // 카테고리 리스트를 모두 가져오는 엔드포인트
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/add")
    public String addCategory() {
        return "admin/category/add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }
}
