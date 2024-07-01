package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, QuerydslPredicateExecutor<OrderItem>
{

}
