package ac.su.kiosk.service;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.dto.IAMPortDTO;
import ac.su.kiosk.logDto.OrderRequest;
import ac.su.kiosk.repository.OrderModuleDTORepository;
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
    private final OrderModuleDTORepository orderModuleDTORepository;

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderByPaymentUid(String paymentUid) {
        return orderRepository.findByPaymentUid(paymentUid);
    }

    public OrderModuleDTO convertToOrderModuleDTO(OrderRequest orderRequest) {
        // OrderRequest를 OrderModuleDTO로 변환하는 로직을 구현
        OrderModuleDTO orderModuleDTO = new OrderModuleDTO();
        orderModuleDTO.setOrderUid(orderRequest.getOrderId());
        orderModuleDTO.setPaymentUid(orderRequest.getPayload());  // 예시로 payload를 paymentUid로 저장
        orderModuleDTO.setPrice(0L);  // 가격 정보가 없으므로 기본값 설정

        // 필요한 필드 추가 설정
        return orderModuleDTO;
    }
}
