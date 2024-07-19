package ac.su.kiosk.service;

import ac.su.kiosk.domain.OrderItem;
import ac.su.kiosk.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItem(long id) {
        return orderItemRepository.findAllByOrderId(id);
    }

    public void deleteOrderItem(long id) {
        orderItemRepository.deleteById((int) id);
    }
}
