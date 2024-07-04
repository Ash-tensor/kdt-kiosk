package ac.su.kiosk.wildmantle.test.repositoryTest;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test-wildmantle.properties")
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Basic Category Generation")
    public void createCategoryList() {
        List<Category> categoryList = new ArrayList<>();

        String[] categoryInputNames = {"Coffee", "Tea", "Ade", "Dessert"};

        for (String categoryName : categoryInputNames) {
            Category category = new Category();
            category.setName(categoryName);
            categoryList.add(category);
        }

        List<Category> savedCategoryList = categoryRepository.saveAll(categoryList);
        System.out.println(savedCategoryList);
    }

}