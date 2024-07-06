package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.domain.CustomOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOptionRepository extends JpaRepository<CustomOption,Long>, QuerydslPredicateExecutor<CustomOption> {
List<CustomOption> findCustomOptionsByMenuId(Long menuID);
}
