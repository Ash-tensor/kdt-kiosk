package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @ManyToOne
    @JoinColumn(name = "kioskID", nullable = false)
    private Kiosk kiosk;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String address;

    @Builder
    public Admin(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

}
