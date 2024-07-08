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
    private  int adminID;

//    for commitment

    @ManyToOne
    @JoinColumn(name = "kioskID", nullable = false) // SPAMMAYO : nullable = false 활성화시 회원가입때 충돌남 수정필요
    private Kiosk kiosk;

    @Column
    private String adminName;

    @Column
    private String password;

}
