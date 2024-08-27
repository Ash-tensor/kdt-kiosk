package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
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

    @Query(value = "SELECT" +
            "    CAST(o.id AS SIGNED) AS orderid, " +
            "    CAST(oi.price AS SIGNED) AS price, " +
            "    CAST(oi.menuID AS SIGNED) AS menuid, " +
            "    hr.gender, " +
            "    CAST(hr.high AS SIGNED) AS high, " +
            "    CAST(hr.low AS SIGNED) AS low, " +
            "    CAST((hr.high + hr.low) / 2 AS DOUBLE) AS average_high_low, " +
            "    CASE " +
            "        WHEN (hr.high + hr.low) / 2 < 20 THEN '10대 초반' " +
            "        WHEN (hr.high + hr.low) / 2 < 30 THEN '10대 후반' " +
            "        WHEN (hr.high + hr.low) / 2 < 40 THEN '20대 초반' " +
            "        WHEN (hr.high + hr.low) / 2 < 50 THEN '20대 후반' " +
            "        ELSE '기타' " +
            "    END AS age_group " +
            "FROM orders AS o " +
            "JOIN orderitem AS oi ON o.id = oi.orderID " +
            "JOIN human_rekognition AS hr ON o.id = hr.order_id " +
            "GROUP BY age_group;",
            nativeQuery = true)
    List<Tuple> getOrderStatistic();



}
