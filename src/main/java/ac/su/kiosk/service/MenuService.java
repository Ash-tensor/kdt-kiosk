package ac.su.kiosk.service;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.CategoryRepository;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public List<Menu> getAllByCategory(Category category) {
        return menuRepository.findAllByCategory(category);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
