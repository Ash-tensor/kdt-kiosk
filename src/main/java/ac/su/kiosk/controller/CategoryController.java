package ac.su.kiosk.controller;

import ac.su.kiosk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryAddRestController categoryAddRestController;



}
