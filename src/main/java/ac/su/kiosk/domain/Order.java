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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kioskID")
    private Kiosk kiosk;

    // 오류를 예방하기 위해 일단 관계없이 생성
    // 추후 위의 JoinColumn 사용

    @Column
    private LocalDateTime dateTime;

    @Column
    private long totalPrice; // 결제모듈의 price와 매핑됨
//
//    @Column
//    private String orderUid; // 결제모듈의 orderUid와 매핑됨

    //@Column
    //private String itemName; // 결제모듈의 itemName과 매핑됨
}
