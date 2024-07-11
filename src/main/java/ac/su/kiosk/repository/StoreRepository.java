package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByAdmin(Admin admin);
}
