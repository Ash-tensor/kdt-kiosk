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
    private int menuId;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
//    private BigDecimal basePrice;
//    int로 수정했습니다.
    private int basePrice;

    @Column(nullable = false)
    private String menuDescription;

    @Column(nullable = false)
    private String menuImage;
}
