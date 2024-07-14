package ac.su.kiosk.controller;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.dto.CustomOptionDTO;
import ac.su.kiosk.dto.CustomOptionRequest;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus/")
public class CustomOptionController {

    private final CustomOptionService customOptionService;
    private final MenuService menuService;

    // 특정 메뉴 ID에 대한 커스텀 옵션 가져오기
    @GetMapping("select-custom-option/{menuId}")
    public List<CustomOption> getCustomOptionsByMenuId(@PathVariable int menuId){
        return customOptionService.getCustomOptionsByMenu(menuId);
    }

    // 모든 커스텀 옵션 가져오기
    @GetMapping("all-custom-options")
    public List<CustomOption> getAllCustomOptions() {
        return customOptionService.getAllCustomOptions();

    }
    // 정렬된 모든 커스텀 옵션 가져오기
    @GetMapping("all-custom-options-with-menu-name")
    public List<CustomOptionDTO> getAllCustomOptionsWithMenuName() {
        return customOptionService.getAllCustomOptionsWithMenuName();
    }

    @PostMapping("add-custom-option")
    public CustomOption addCustomOption(@RequestBody CustomOptionRequest request) {
        return customOptionService.addCustomOption(request.getName(), request.getAdditionalPrice(), request.getMenuId());
    }

    @DeleteMapping("delete-custom-option/{id}")
    public void deleteCustomOption(@PathVariable Long id) {
        customOptionService.deleteCustomOption(id);
    }
}