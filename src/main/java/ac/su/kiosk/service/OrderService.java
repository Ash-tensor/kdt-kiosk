package ac.su.kiosk.service;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderByPaymentUid(String paymentUid) {
        return orderRepository.findByPaymentUid(paymentUid);
    }

}
