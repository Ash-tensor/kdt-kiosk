package ac.su.kiosk.dto;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.domain.Store;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class OrderModuleDTO {
    private Long price;
    private String storeName;
    private String email;
    private String address;
    private PaymentStatus status;

    // 자동생성됨
    private String paymentUid;
    private String orderUid;

    public OrderModuleDTO(Order order, Payment payment, Store store, Admin admin) {
        this.price = order.getTotalPrice();
        this.storeName = store.getName();
        this.email = admin.getEmail();
        this.address = store.getLocation();
        this.status = payment.getPaymentStatus();
    }
}
