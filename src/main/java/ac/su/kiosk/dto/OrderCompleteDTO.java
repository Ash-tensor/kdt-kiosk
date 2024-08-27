package ac.su.kiosk.dto;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Data
public class OrderCompleteDTO {
    private Long id;
    private List<OrderItemDTO> orderItemList;
    private LocalDateTime datetime;
    private Boolean complete;
    private Long orderId;
}
