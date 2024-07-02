package ac.su.kiosk.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter @Setter
@Table(name = "categoryoption")
public class CustomOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customOptionId;

    @ManyToOne
    @JoinColumn(name = "menuId", nullable = false)
    private Category menu;

    @Column(nullable = false)
    private String optionName;

    @Column(nullable = false)
    private BigDecimal additionalPrice;

}
