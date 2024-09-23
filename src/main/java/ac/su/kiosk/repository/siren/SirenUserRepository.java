package ac.su.kiosk.repository.siren;

import ac.su.kiosk.domain.SirenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SirenUserRepository extends JpaRepository<SirenUser, Long> {
    Optional<SirenUser> findByName(String name);
}
