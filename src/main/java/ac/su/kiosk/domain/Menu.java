package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuID;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private int basePrice;

    @Column
    private String menuDescription;

    @Column(length = 2000)
    private String menuImage;
}
