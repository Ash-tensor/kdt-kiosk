package ac.su.kiosk.service;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.CategoryRepository;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final StorageService storageService;
    private final CategoryRepository categoryRepository;

    public void addMenu(String menuDescription, MultipartFile file,
                        String menuName, int categoryID, int basePrice) throws IOException {
        Menu menu = new Menu();

        String menuImage = storageService.uploadFile(file);

        menu.setMenuDescription(menuDescription);
        menu.setMenuImage(menuImage);
        menu.setMenuName(menuName);
        menu.setBasePrice(basePrice);

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryID));
        menu.setCategory(category);

        menuRepository.save(menu);
    }
}
