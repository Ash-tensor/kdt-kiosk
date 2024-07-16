package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.domain.OptionItem;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.OptionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/custom-options")
public class CustomOptionController {

    private final CustomOptionService customOptionService;
    private final OptionItemService optionItemService;

//    특정 ID의 CustomOption을 조회
    @GetMapping("/{id}")
    public ResponseEntity<CustomOption> getCustomOptionById(@PathVariable Long id) {
        CustomOption customOption = customOptionService.getCustomOptionById(id);
        return ResponseEntity.ok(customOption);
    }

//    모든 CustomOption을 조회
    @GetMapping
    public ResponseEntity<List<CustomOption>> getAllCustomOptions() {
        List<CustomOption> customOptions = customOptionService.getAllCustomOptions();
        return ResponseEntity.ok(customOptions);
    }

//    새로운 CustomOption을 생성
    @PostMapping
    public ResponseEntity<CustomOption> createCustomOption(@RequestBody CustomOption customOption) {
        CustomOption createdCustomOption = customOptionService.createCustomOption(customOption.getName(), customOption.isMandatory(), customOption.getMenu().getId());
        return ResponseEntity.ok(createdCustomOption);
    }

//    새로운 CustomOption을 생성
    @PutMapping("/{id}")
    public ResponseEntity<CustomOption> updateCustomOption(@PathVariable Long id, @RequestBody CustomOption customOption) {
        CustomOption updatedCustomOption = customOptionService.updateCustomOption(id, customOption);
        return ResponseEntity.ok(updatedCustomOption);
    }

//    특정 ID의 CustomOption을 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomOption(@PathVariable Long id) {
        customOptionService.deleteCustomOption(id);
        return ResponseEntity.noContent().build();
    }

//    특정 CustomOption에 속한 모든 OptionItem을 조회
    @GetMapping("/{optionId}/items")
    public ResponseEntity<List<OptionItem>> getOptionItems(@PathVariable Long optionId) {
        CustomOption customOption = customOptionService.getCustomOptionById(optionId);
        if (customOption != null) {
            return ResponseEntity.ok(customOption.getItems());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    특정 CustomOption에 새로운 OptionItem을 추가
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

//    특정 ID의 OptionItem을 업데이트
    @PutMapping("/items/{itemId}")
    public ResponseEntity<OptionItem> updateOptionItem(@PathVariable Long itemId, @RequestBody OptionItem optionItem) {
        OptionItem updatedOptionItem = optionItemService.updateOptionItem(itemId, optionItem);
        if (updatedOptionItem != null) {
            return ResponseEntity.ok(updatedOptionItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    특정 ID의 OptionItem을 삭제
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteOptionItem(@PathVariable Long itemId) {
        optionItemService.deleteOptionItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
