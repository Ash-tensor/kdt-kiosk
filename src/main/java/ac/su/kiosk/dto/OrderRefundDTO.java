package ac.su.kiosk.dto;

import ac.su.kiosk.domain.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Data
public class OrderRefundDTO {
    private Long id;
    private int customerId;
    private int kioskId;
    private LocalDateTime dateTime;
    private long totalPrice;
    private String paymentUid;

    public OrderRefundDTO(Long id, int customerId, int kioskId, LocalDateTime dateTime, long totalPrice, String paymentUid) {
        this.id = id;
        this.customerId = customerId;
        this.kioskId = kioskId;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.paymentUid = paymentUid;
    }

    // 해당 방식은 new를 호출해야하기 때문에 권장되지 않음
    public OrderRefundDTO(Order order) {
        new OrderRefundDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getKiosk().getId(),
                order.getDateTime(),
                order.getTotalPrice(),
                order.getPaymentUid()
        );
    }

    // static으로 클래스에서 함수 사용가능
    public static OrderRefundDTO fromOrder(Order order) {
        return new OrderRefundDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getKiosk().getId(),
                order.getDateTime(),
                order.getTotalPrice(),
                order.getPaymentUid()
        );
    }
}
