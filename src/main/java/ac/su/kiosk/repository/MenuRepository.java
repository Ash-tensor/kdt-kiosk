package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByCategory(Category category);
    List<Menu> findByCategoryId(Long categoryId);
}
