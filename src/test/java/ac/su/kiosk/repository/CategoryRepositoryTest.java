package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test-wildmantle.properties")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    void createDummyData() {
        // 기존 데이터를 모두 삭제
        categoryRepository.deleteAll();

        // 더미 카테고리 데이터 생성 및 저장
        Category category1 = new Category();
        category1.setName("Coffee");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Cold Brew");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("Non-Coffee");
        categoryRepository.save(category3);
    }

    @Test
    void findById() {
        // 테스트용 카테고리 생성 및 저장
        createDummyData();

        // ID로 카테고리 검색
        Optional<Category> foundCategory = categoryRepository.findById(category.getId());

        // 검색된 카테고리 출력
        System.out.println("findById - Found Category: " + foundCategory);

        // 카테고리가 잘 찾아졌는지 확인하고, 이름이 예상과 같은지 확인
        assertTrue(foundCategory.isPresent());
        assertEquals("Test Category", foundCategory.get().getName());
    }

    @Test
    void findByName() {
        // Create and save a test category
        Category category = new Category();
        category.setName("Unique Category");
        category = categoryRepository.save(category);

        // Retrieve the category by name
        Optional<Category> foundCategory = categoryRepository.findByName("Unique Category");

        // Assert that the category was found and has the expected name
        assertTrue(foundCategory.isPresent());
        assertEquals("Unique Category", foundCategory.get().getName());
    }
}
