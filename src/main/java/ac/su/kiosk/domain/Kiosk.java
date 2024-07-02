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

    private String kioskNumber;

    @OneToMany(mappedBy = "kiosk")
    private List<Admin> admins;

    public int getKioskID() {
        return kioskID;
    }

    public void setKioskID(int kioskID) {
        this.kioskID = kioskID;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getKioskNumber() {
        return kioskNumber;
    }

    public void setKioskNumber(String kioskNumber) {
        this.kioskNumber = kioskNumber;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
}
