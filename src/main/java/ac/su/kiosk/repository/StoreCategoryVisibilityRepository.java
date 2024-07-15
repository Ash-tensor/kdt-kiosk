package ac.su.kiosk.repository;

import ac.su.kiosk.domain.StoreCategoryVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreCategoryVisibilityRepository extends JpaRepository<StoreCategoryVisibility, Long> {
    List<StoreCategoryVisibility> findByStoreId(Long storeId);
}
