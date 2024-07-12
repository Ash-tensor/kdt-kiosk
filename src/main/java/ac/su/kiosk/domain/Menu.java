package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private String description;

    @Column(length = 2000)
    private String image;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "menu_options", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "option")
    private List<String> options;

    @Column
    private boolean soldOut;

    @Column
    private String tags;
}
