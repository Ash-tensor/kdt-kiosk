package ac.su.kiosk.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kioskID")
    private Kiosk kiosk;

    // 오류를 예방하기 위해 일단 관계없이 생성
    // 추후 위의 JoinColumn 사용

    @Column
    private LocalDateTime orderDateTime;

    @Column
    private int totalPrice;
}
