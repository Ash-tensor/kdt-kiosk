package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name="store")
public class Store {
    @Id //이 클래스의 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본 키 필드의 값이 데이터베이스의 자동 증가 기능을 사용하여 생성
    private int storeID;

    @Column
    private String storeName;
    @Column
    private String location;

    @OneToMany(mappedBy = "store")//일대다 관계
    private List<Kiosk> kiosks;
}
