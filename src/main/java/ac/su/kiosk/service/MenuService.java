package ac.su.kiosk.service;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> getMenuByCategoryAndName(String categoryName, String menuName) {
        return menuRepository.findByCategory_CategoryNameAndMenuName(categoryName, menuName);
    }
}
