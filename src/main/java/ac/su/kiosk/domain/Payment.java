package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PaymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order")
    private Order OrderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaymentMethod")
    private PaymentMethod PaymentMethodID;

    @Column
    private Long Amount;

    @Column
    private Date PaymentDateTime;
}
