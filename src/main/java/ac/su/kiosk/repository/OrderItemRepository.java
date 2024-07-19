package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, QuerydslPredicateExecutor<OrderItem>
{
    List<OrderItem> findAllByOrderId(Long orderId);

}
