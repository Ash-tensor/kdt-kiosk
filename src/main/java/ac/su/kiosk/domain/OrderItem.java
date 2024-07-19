package ac.su.kiosk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "orderitem")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menuID",nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "custom_optionID")
    private CustomOption customOption;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    Long price;

}
