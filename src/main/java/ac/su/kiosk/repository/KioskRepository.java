package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Kiosk;
import ac.su.kiosk.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KioskRepository extends JpaRepository<Kiosk, Integer> {
    List<Kiosk> findByStore(Store store);
    Optional<Kiosk> findByNumber(String number);
}
