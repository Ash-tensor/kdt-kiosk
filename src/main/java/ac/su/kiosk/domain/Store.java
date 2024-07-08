package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="store")
public class Store {
    @Id //이 클래스의 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본 키 필드의 값이 데이터베이스의 자동 증가 기능을 사용하여 생성
    private int id;

    @Column
    private String name;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "adminID", nullable = false)
    private Admin admin;

//    @OneToMany(mappedBy = "store")//일대다 관계
//    private List<Kiosk> kiosks;
}
