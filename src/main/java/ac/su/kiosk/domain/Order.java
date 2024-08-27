package ac.su.kiosk.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;



@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kioskID", nullable = false)
    private Kiosk kiosk;

    @Column(name="date_time",nullable = false)
    private LocalDateTime dateTime;

    @Column(name="total_price",nullable = false)
    private long totalPrice;

    @Column(name="is_packaged",nullable = false)
    private boolean isPackaged;

    @Column(name="payment_uid",nullable = false)
    private String paymentUid;

    // 결제 환불을 위한 join
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_module_dto")
    private OrderModuleDTO orderModuleDTO;

}
