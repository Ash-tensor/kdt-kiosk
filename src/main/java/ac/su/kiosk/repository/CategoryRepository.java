package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, QuerydslPredicateExecutor<Category>
{
    List<Category> findByCategoryName(String name);
}
