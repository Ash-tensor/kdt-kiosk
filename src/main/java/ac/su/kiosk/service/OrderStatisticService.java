package ac.su.kiosk.service;

import ac.su.kiosk.dto.OrderStatisticDTO;
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

    public List<OrderStatisticDTO> convertToDTO(List<Tuple> tuples) {
        return tuples.stream()
                .map(tuple -> new OrderStatisticDTO(
                        tuple.get(0, Long.class),
                        tuple.get(1, Long.class),
                        tuple.get(2, Long.class),
                        tuple.get(3, Boolean.class),
                        tuple.get(4, Long.class),
                        tuple.get(5, Long.class),
                        tuple.get(6, BigDecimal.class),
                        tuple.get(7, String.class)
                ))
                .collect(Collectors.toList());
    }
}
