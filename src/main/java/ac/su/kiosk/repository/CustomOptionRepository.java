package ac.su.kiosk.repository;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.dto.CustomOptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOptionRepository extends JpaRepository<CustomOption,Long>, QuerydslPredicateExecutor<CustomOption> {

    List<CustomOption> findCustomOptionsByMenuId(int menu_id);

    @Query("SELECT new ac.su.kiosk.dto.CustomOptionDTO(co.id, co.name, co.additionalPrice, m.name) " +
            "FROM CustomOption co JOIN co.menu m")
    List<CustomOptionDTO> findAllCustomOptionsWithMenuName();
}
