package ac.su.kiosk.dto;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class TempOrderModuleDTO {
    private Long price;
    private String storeName;
    private String email;
    private String address;
    private PaymentStatus status;

    // 자동생성됨
    private String paymentUid;
    private String orderUid;

    public TempOrderModuleDTO(Payment payment, Admin admin) {
        this.price = payment.getAmount();
        Kiosk kiosk = admin.getKiosk();
        this.storeName = kiosk.getStore().getName();
        this.address = kiosk.getStore().getLocation();
        this.email = admin.getEmail();
        this.status = payment.getPaymentStatus();
//        this.paymentUid = UUID.randomUUID().toString();
        this.orderUid = UUID.randomUUID().toString();

    }
}
