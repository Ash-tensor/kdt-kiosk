package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.domain.OptionItem;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.OptionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/custom-options")
public class CustomOptionController {

    private final CustomOptionService customOptionService;
    private final OptionItemService optionItemService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomOption> getCustomOptionById(@PathVariable Long id) {
        CustomOption customOption = customOptionService.getCustomOptionById(id);
        return ResponseEntity.ok(customOption);
    }

    @GetMapping
    public ResponseEntity<List<CustomOption>> getAllCustomOptions() {
        List<CustomOption> customOptions = customOptionService.getAllCustomOptions();
        return ResponseEntity.ok(customOptions);
    }

    @PostMapping
    public ResponseEntity<CustomOption> createCustomOption(@RequestBody CustomOption customOption) {
        if (customOption.getItems() == null) {
            customOption.setItems(new ArrayList<>());
        }
        CustomOption createdCustomOption = customOptionService.createCustomOption(
                customOption.getName(), customOption.isMandatory(), customOption.getMenu().getId(), customOption.getItems());
        return ResponseEntity.ok(createdCustomOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomOption> updateCustomOption(@PathVariable Long id, @RequestBody CustomOption customOption) {
        if (customOption.getItems() == null) {
            customOption.setItems(new ArrayList<>());
        }
        CustomOption updatedCustomOption = customOptionService.updateCustomOption(id, customOption);
        return ResponseEntity.ok(updatedCustomOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomOption(@PathVariable Long id) {
        customOptionService.deleteCustomOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{optionId}/items")
    public ResponseEntity<List<OptionItem>> getOptionItems(@PathVariable Long optionId) {
        CustomOption customOption = customOptionService.getCustomOptionById(optionId);
        if (customOption != null) {
            return ResponseEntity.ok(customOption.getItems());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{optionId}/items")
    public ResponseEntity<OptionItem> addOptionItem(@PathVariable Long optionId, @RequestBody OptionItem optionItem) {
        CustomOption customOption = customOptionService.getCustomOptionById(optionId);
        if (customOption != null) {
            optionItem.setCustomOption(customOption);
            OptionItem createdOptionItem = optionItemService.createOptionItem(optionItem);
            return ResponseEntity.ok(createdOptionItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<OptionItem> updateOptionItem(@PathVariable Long itemId, @RequestBody OptionItem optionItem) {
        OptionItem updatedOptionItem = optionItemService.updateOptionItem(itemId, optionItem);
        if (updatedOptionItem != null) {
            return ResponseEntity.ok(updatedOptionItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteOptionItem(@PathVariable Long itemId) {
        optionItemService.deleteOptionItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
