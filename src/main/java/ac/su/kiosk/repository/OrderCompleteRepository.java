package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OrderComplete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCompleteRepository extends JpaRepository<OrderComplete, Long> {
}
