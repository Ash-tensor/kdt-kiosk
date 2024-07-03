package ac.su.kiosk.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter @Setter
@Table(name = "custom_option")
public class CustomOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customOptionID;

    @ManyToOne
    @JoinColumn(name = "menuID", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String optionName;

    @Column(nullable = false)
    private int additionalPrice;

}
