package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findById(Integer id);

    Optional<Admin> findByName(String name);

    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
}
