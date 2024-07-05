package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KioskRepostiory extends JpaRepository<Kiosk, Integer> {
    Optional<Kiosk> findById(Integer id);
}
