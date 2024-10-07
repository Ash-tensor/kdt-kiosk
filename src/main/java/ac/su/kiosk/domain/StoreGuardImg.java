package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="storeGuardImg")
public class StoreGuardImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(length = 2000)
    public String imgUrl;

    @ManyToOne//다대일 관계
    @JoinColumn(name = "storeID", nullable = false)//외래키 필드의 이름
    private Store store;
}
