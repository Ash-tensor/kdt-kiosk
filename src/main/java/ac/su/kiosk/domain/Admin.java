package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

//     @ManyToOne
//     @JoinColumn(name = "storeID", nullable = false)
//     private Store store;//가 연결되어 있어야함

//    @ManyToOne
//    @JoinColumn(name = "kioskID", nullable = false) // SPAMMAYO : nullable = false 활성화시 회원가입때 충돌남 수정필요
//    private Kiosk kiosk;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email; // 결제모듈 추가로 인해 추가됨

}
