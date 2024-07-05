package ac.su.kiosk.domain;

import ac.su.kiosk.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentMethodID")
    private PaymentMethod method;

    @Column
    private Long amount;

    @Column
    private Date dateTime;

    @Column
    private PaymentStatus paymentStatus;
}
