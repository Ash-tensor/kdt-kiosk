package ac.su.kiosk.service;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuUpdateService {
    private final MenuRepository menuRepository;

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public List<Menu> getMenusByCategory(Long categoryId) {
        return menuRepository.findByCategoryId(categoryId);
    }

    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(int id, Menu menu) {
        Menu existingMenu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid menu ID"));
        existingMenu.setName(menu.getName());
        existingMenu.setPrice(menu.getPrice());
        existingMenu.setDescription(menu.getDescription());
        existingMenu.setImage(menu.getImage());
        existingMenu.setCategory(menu.getCategory());
        // options 필드를 제거한 후 수정된 코드
        // existingMenu.setOptions(menu.getOptions()); // 이 줄을 제거합니다.
        existingMenu.setSoldOut(menu.isSoldOut());
        existingMenu.setTags(menu.getTags());
        return menuRepository.save(existingMenu);
    }

    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }
}
