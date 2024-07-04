package ac.su.kiosk.service;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.repository.OrderRepository;
import ac.su.kiosk.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Order autoOrder(Admin admin, Long itemPrice) {
        Payment payment = Payment.builder()
                .amount(itemPrice)
                .status(PaymentStatus.READY)
                .build();

        paymentRepository.save(payment);

        Order order = Order.builder

    }


}
