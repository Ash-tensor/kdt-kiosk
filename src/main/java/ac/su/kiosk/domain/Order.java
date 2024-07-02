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
    private Long OrderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")  // 여기서 부터는 임의로 지은 테이블 명 및 클래스 명 이용 추후 수정 예정
    private Customer CustomerID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KioskID")
    private Kiosk KioskID;

    // 오류를 예방하기 위해 일단 관계없이 생성
    // 추후 위의 JoinColumn 사용

    @Column
    private LocalDateTime OrderDateTime;

    @Column
    private BigDecimal TotalPrice;
}
