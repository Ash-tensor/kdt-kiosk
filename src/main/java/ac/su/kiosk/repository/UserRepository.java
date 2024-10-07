package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Store;
import ac.su.kiosk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    Optional<User> findByStore(Store store);
}
