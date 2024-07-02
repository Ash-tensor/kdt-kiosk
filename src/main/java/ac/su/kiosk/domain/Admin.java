package ac.su.kiosk.domain;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int adminID;

    @ManyToOne
    @JoinColumn(name = "kioskID", nullable = false)
    private Kiosk kiosk;

    @Column
    private String adminName;
    @Column
    private String password;

}
