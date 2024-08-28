package ac.su.kiosk.service;

import ac.su.kiosk.dto.OrderStatisticDTO;
import ac.su.kiosk.repository.OrderItemRepository;
import ac.su.kiosk.repository.OrderRepository;
import jakarta.persistence.Tuple;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter @Setter
@Service
public class OrderStatisticService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<OrderStatisticDTO> getOrderItemsWithHumanRekognition() {
        return orderItemRepository.fetchOrderItemsWithHumanRekognition();

    }
}
