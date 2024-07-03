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
    @JoinColumn(name = "kioskID", nullable = false)
    private Kiosk kiosk;

    @Column
    private String adminName;

    @Column
    private String password;

}
