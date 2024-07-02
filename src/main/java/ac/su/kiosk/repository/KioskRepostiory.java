package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Kiosk;
import ac.su.kiosk.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KioskRepostiory extends JpaRepository<Kiosk, Integer> {
    List<Kiosk>findByKioskID(Integer kioskID);
}
