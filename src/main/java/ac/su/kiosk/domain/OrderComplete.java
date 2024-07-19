package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "order_complete")
public class OrderComplete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean complete;

    @JoinColumn(name = "orderID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
