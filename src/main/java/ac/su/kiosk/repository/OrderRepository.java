package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.OrderStatisticDTO;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    List<Order> findByDateTime(LocalDateTime startDateTime);

    List<Order> findAllByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Optional<Order> findByPaymentUid(String paymentUid);

    @Query("SELECT o " +
            "FROM Order o JOIN FETCH o.customer JOIN FETCH o.kiosk JOIN FETCH o.orderModuleDTO")
    List<Order> findAllOrder();
}





