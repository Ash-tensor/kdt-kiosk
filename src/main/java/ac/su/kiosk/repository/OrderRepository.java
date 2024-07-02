package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_CustomerID(Long customerID);
    List<Order> findByKiosk_KioskID(Long kioskID);
    List<Order> findByOrderedDateTime(LocalDateTime startDateTime);
}
