package ac.su.kiosk.controller;

import ac.su.kiosk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/category")
@RequiredArgsConstructor
@RestController
public class CategoryDeleteRestController {
    private final CategoryService categoryService;

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestParam(value = "id", required = false) Integer id ) {
        if (id == null) throw new IllegalArgumentException("id is required");
        else {
            categoryService.deleteCategory(id);
        }
    }
}
