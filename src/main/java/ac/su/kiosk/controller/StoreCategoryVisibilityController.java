package ac.su.kiosk.controller;

import ac.su.kiosk.domain.StoreCategoryVisibility;
import ac.su.kiosk.service.StoreCategoryVisibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store-category-visibility")
@RequiredArgsConstructor
public class StoreCategoryVisibilityController {
    private final StoreCategoryVisibilityService service;

    @GetMapping("/{storeId}")
    public ResponseEntity<List<StoreCategoryVisibility>> getVisibilityByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(service.getVisibilityByStore(storeId));
    }

    @PostMapping
    public ResponseEntity<StoreCategoryVisibility> setVisibility(@RequestBody StoreCategoryVisibility visibility) {
        return ResponseEntity.ok(service.setVisibility(visibility));
    }
}
