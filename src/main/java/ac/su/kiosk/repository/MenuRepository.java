package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>, QuerydslPredicateExecutor<Menu> {
    List<Menu> findByCategory_CategoryNameAndMenuName(String categoryName, String menuName);
}
