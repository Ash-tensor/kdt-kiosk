package ac.su.kiosk.service;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.dto.MenuDTO;
import ac.su.kiosk.repository.CategoryRepository;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final StorageService storageService;
    private final CategoryRepository categoryRepository;

    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }

    public String addMenu(String menuDescription, MultipartFile file,
                        String menuName, int categoryID, long price) throws IOException {
        Menu menu = new Menu();

        String menuImage = storageService.uploadFile(file);

        menu.setDescription(menuDescription);
        menu.setImage(menuImage);
        menu.setName(menuName);
        menu.setPrice(price);

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryID));
        menu.setCategory(category);

        menuRepository.save(menu);
        return menuImage;
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public List<Menu> getAllByCategory(Category category) {
        return menuRepository.findAllByCategory(category);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Menu> getMenusByCategory(Long categoryId) {
        return menuRepository.findByCategoryId(categoryId);
    }

    // 새로운 메서드: 파일 없이 메뉴 추가
    public void addMenuSimple(MenuDTO menuDto) {
        Menu menu = new Menu();
        menuCheck(menuDto, menu);
        menuRepository.save(menu);
    }

    // 새로운 메서드: 메뉴 수정
    public Menu updateMenu(int id, MenuDTO menuDto) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        menuCheck(menuDto, existingMenu);
        return menuRepository.save(existingMenu);
    }

    // add 및 update 에서 중복하여 사용되는 메서드
    private void menuCheck(MenuDTO menuDto, Menu existingMenu) {
        existingMenu.setName(menuDto.getName());
        Category category = categoryRepository.findById(menuDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + menuDto.getCategoryId()));
        existingMenu.setCategory(category);
        existingMenu.setPrice(menuDto.getPrice());
        existingMenu.setSoldOut(menuDto.isSoldOut());
        existingMenu.setTag(menuDto.getTag());
    }
}
