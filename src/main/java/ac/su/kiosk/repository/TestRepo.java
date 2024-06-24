package ac.su.kiosk.repository;

import ac.su.kiosk.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepo extends JpaRepository<TestEntity, Long> {
}
