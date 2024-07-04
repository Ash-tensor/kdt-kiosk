package ac.su.kiosk.service;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Optional<Menu> getMenusByCategory(Long id) {
        return menuRepository.findById(id);
    }

    public Menu getMenu(Long id) {
        return menuRepository.findById(id).orElse(null);
    }
}
