package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.domain.StoreMenuAvailability;
import ac.su.kiosk.service.MenuService;
import ac.su.kiosk.service.StoreMenuAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuSearchController {

    private final MenuService menuService;
    private final StoreMenuAvailabilityService availabilityService;

    // 모든 메뉴를 검색해서 반환 ( JSON 데이터 형식 )
    @GetMapping("/all")
    public List<Menu> getAllMenu() {
        return menuService.getAll();
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
}
