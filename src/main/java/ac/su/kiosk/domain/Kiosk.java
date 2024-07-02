package ac.su.kiosk.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Kiosk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int kioskID;

    @ManyToOne//다대일 관계
    @JoinColumn(name = "storeID", nullable = false)//외래키 필드의 이름
    private Store store;

    @Column
    private String kioskNumber;

    @OneToMany(mappedBy = "kiosk")
    private List<Admin> admins;

}
