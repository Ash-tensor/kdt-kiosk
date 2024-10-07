package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.domain.StoreMenuAvailability;
import ac.su.kiosk.dto.CustomOptionDTO;
import ac.su.kiosk.dto.CustomOptionRequest;
import ac.su.kiosk.dto.MenuNameAndPriceDTO;
import ac.su.kiosk.service.CategoryService;
import ac.su.kiosk.service.CustomOptionService;
import ac.su.kiosk.service.MenuService;
import ac.su.kiosk.service.StoreMenuAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuSearchController {

    private final MenuService menuService;
    private final StoreMenuAvailabilityService availabilityService;
    private final CategoryService categoryService;
    private final CustomOptionService customOptionService;

    // 모든 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/all")
    public List<Menu> getAllMenu() {
        return menuService.getAll();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 카테고리 ID로 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/{categoryId}")
    public List<Menu> getMenusByCategory(@PathVariable Long categoryId) {
        return menuService.getMenusByCategory(categoryId);
    }

    // 매장 ID로 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/store/{storeId}")
    public List<Menu> getMenusByStore(@PathVariable Long storeId) {
        List<StoreMenuAvailability> availabilityList = availabilityService.getAvailabilityByStore(storeId);
        return availabilityList.stream()
                .filter(StoreMenuAvailability::isAvailable)
                .map(StoreMenuAvailability::getMenu)
                .collect(Collectors.toList());
    }

    // 매장 ID와 카테고리 ID로 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/store/{storeId}/category/{categoryId}")
    public List<Menu> getMenusByStoreAndCategory(@PathVariable Long storeId, @PathVariable Long categoryId) {
        List<Menu> menus = menuService.getMenusByCategory(categoryId);
        List<StoreMenuAvailability> availabilityList = availabilityService.getAvailabilityByStore(storeId);

        return menus.stream()
                .filter(menu -> availabilityList.stream()
                        .anyMatch(availability -> availability.getMenu().getId() == menu.getId() && availability.isAvailable()))
                .collect(Collectors.toList());
    }

    @GetMapping("/menuNameandPrice")
    public List<MenuNameAndPriceDTO> getMenuNameAndPrice() {
        List<MenuNameAndPriceDTO> menuDTOs = new ArrayList<>();
        List<Menu> menus = menuService.getAll();
        for (Menu menu : menus) {
            MenuNameAndPriceDTO menuDTO = new MenuNameAndPriceDTO();
            menuDTO.setName(menu.getName());
            menuDTO.setPrice(menu.getPrice());
            menuDTOs.add(menuDTO);
        }
        return menuDTOs;
    }

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
