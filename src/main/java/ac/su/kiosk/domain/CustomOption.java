package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "custom_option")
public class CustomOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double additionalPrice;

    @ManyToOne
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;
}
