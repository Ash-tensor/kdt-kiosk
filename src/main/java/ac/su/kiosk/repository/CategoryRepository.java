package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, QuerydslPredicateExecutor<Category>
{
    Optional<Category> findById(Long id);
    List<Category> findByName(String name);
}
