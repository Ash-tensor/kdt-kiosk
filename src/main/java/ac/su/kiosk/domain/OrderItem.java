package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "menuID",nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "customOptionID",nullable = false)
    private CustomOption customOption;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    BigDecimal itemPrice;
}
