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

    private String adminName;
    private String password;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Kiosk getKiosk() {
        return kiosk;
    }

    public void setKiosk(Kiosk kiosk) {
        this.kiosk = kiosk;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
