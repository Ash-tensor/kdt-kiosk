package ac.su.kiosk.service;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.OrderRefundDTO;
import ac.su.kiosk.repository.OrderModuleDTORepository;
import ac.su.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RefundPaymentService {
    private final OrderRepository orderRepository;

    // 모든 결제 내역 가져오기
    public List<OrderRefundDTO> getAllOrders(){
        List<Order> orders = orderRepository.findAllOrder();
        List<OrderRefundDTO> orderRefundDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderRefundDTOs.add(OrderRefundDTO.fromOrder(order));
        }
        return orderRefundDTOs;
    }

    public String getPaymentUid(Long orderId){
        return orderRepository.findById(orderId).get().getPaymentUid();
    }

    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }
}
