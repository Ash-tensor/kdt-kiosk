package ac.su.kiosk.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Store {
    @Id //이 클래스의 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본 키 필드의 값이 데이터베이스의 자동 증가 기능을 사용하여 생성
    private int storeID;

    private String storeName;
    private String location;

    @OneToMany(mappedBy = "store")//일대다 관계
    private List<Kiosk> kiosks;

    //Getters and setters

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Kiosk> getKiosks() {
        return kiosks;
    }

    public void setKiosks(List<Kiosk> kiosks) {
        this.kiosks = kiosks;
    }
}
