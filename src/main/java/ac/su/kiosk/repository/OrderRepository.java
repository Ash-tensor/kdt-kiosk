package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    List<Order> findByDateTime(LocalDateTime startDateTime);

    List<Order> findAllByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Optional<Order> findByPaymentUid(String paymentUid);
}
