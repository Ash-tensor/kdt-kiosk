package ac.su.kiosk.domain;

import ac.su.kiosk.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ordermoduledto")
public class OrderModuleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Long price;
    @Column
    private String storeName;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private PaymentStatus status;

    // 자동생성됨
    @Column
    private String paymentUid;
    @Column
    private String orderUid;

    public void changePaymentBySuccess(PaymentStatus status, String paymentUid) {
        this.status = status;
        this.paymentUid = paymentUid;
    }

}
