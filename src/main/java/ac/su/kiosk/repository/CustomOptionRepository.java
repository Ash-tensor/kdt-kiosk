package ac.su.kiosk.repository;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.dto.CustomOptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOptionRepository extends JpaRepository<CustomOption, Long> {

    List<CustomOption> findCustomOptionsByMenuId(int menuId);

    @Query("SELECT new ac.su.kiosk.dto.CustomOptionDTO(co.id, co.name, oi.price, m.name) " +
            "FROM CustomOption co " +
            "JOIN co.menu m " +
            "JOIN co.items oi")
    List<CustomOptionDTO> findAllCustomOptionsWithMenuNameAndPrice();

    @Query("SELECT new ac.su.kiosk.dto.CustomOptionDTO(co.id, co.name, co.additionalPrice, m.name) " +
            "FROM CustomOption co JOIN co.menu m")
    List<CustomOptionDTO> findAllCustomOptionsWithMenuName();
}
