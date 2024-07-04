package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/category")
@RestController
public class CategoryAddController {
    private final CategoryService categoryService;

    @GetMapping("/add")
    public String addCategory() {
        return "admin/category/add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }
}
