package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "custom_option")
public class CustomOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 옵션 이름 'SIZE' 시 - large, medium, small 로 react 에서 설정 가능하게 하는 옵션임!
    @Column
    private String name;

    @Column
    private Double additionalPrice;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
