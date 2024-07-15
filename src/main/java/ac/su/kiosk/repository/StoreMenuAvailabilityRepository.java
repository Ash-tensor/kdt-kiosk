package ac.su.kiosk.repository;

import ac.su.kiosk.domain.StoreMenuAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreMenuAvailabilityRepository extends JpaRepository<StoreMenuAvailability, Long> {
    List<StoreMenuAvailability> findByStoreId(Long storeId);
}
