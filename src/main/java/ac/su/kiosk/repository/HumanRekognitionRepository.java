package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.domain.HumanRekognitionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRekognitionRepository extends JpaRepository<HumanRekognitionResult, Long> {
}
