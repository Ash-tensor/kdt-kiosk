package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByAdminID(Integer adminID);
}
