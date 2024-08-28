package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderItem;
import ac.su.kiosk.dto.OrderStatisticDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Map;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, QuerydslPredicateExecutor<OrderItem>
{
    List<OrderItem> findAllByOrderId(Long orderId);

    @Transactional
    @Query("SELECT oi " +
            "FROM OrderItem oi " +
            "JOIN FETCH oi.menu m " +
            "WHERE oi.order.id IN :orderIdList")
    List<OrderItem> findAllByOrderIdIn(List<Long> orderIdList);

    @Query("SELECT new ac.su.kiosk.dto.OrderStatisticDTO (" +
            "oi.order.id, " +
            "oi.price, " +
            "oi.menu.id, " +
            "hr.gender, " +
            "hr.high, " +
            "hr.low, " +
            "(hr.high + hr.low) / 2.0" +
            ") " +
            "FROM OrderItem oi " +
            "JOIN HumanRekognitionResult hr ON oi.order.id = hr.order.id")
    List<OrderStatisticDTO> fetchOrderItemsWithHumanRekognition();
}
