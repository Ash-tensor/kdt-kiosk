package ac.su.kiosk.service;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    // 카테고리 리스트를 모두 가져오는 메서드 추가
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
