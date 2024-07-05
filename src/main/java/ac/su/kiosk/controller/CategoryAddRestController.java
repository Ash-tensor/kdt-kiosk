package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/category")
@RestController
public class CategoryAddRestController {
    private final CategoryService categoryService;

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
