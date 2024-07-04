package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column
    private String customerName;

    @Column
    private String customerPhone;

    @Column
    private String customerAddress;

    // 추가해야 할 컬럼(결제모듈을 위해서)
//    @Column
//    private String email;
//    @Column
//    private String address;
}