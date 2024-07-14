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
    @JoinColumn(name = "customerID", nullable = false)
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

//    @Column(nullable = false)
//    private String orderUid;
    //    @Column
//    private String orderUid; // 결제모듈의 orderUid와 매핑됨

    //@Column
    //private String itemName; // 결제모듈의 itemName과 매핑됨
}
