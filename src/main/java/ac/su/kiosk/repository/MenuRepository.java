package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>, QuerydslPredicateExecutor<Menu>
{
    Optional<Menu> findById(Long id);
}
