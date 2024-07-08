package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name="kiosk")
public class Kiosk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @ManyToOne//다대일 관계
    @JoinColumn(name = "storeID", nullable = false)//외래키 필드의 이름
    private Store store;

    @Column // 이 넘버가 뭐지? 굳이 필요한가? << 일련번호인듯
    private String number;

//    @OneToMany(mappedBy = "kiosk")
//    private List<Admin> admins;

}
