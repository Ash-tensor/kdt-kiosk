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
    private int id;

    @Column
    private String name;

    @Column(name="phone_number",nullable=false)
    private String phoneNumber;

    @Column
    private int points;

    @Column
    private String password;

//    @Column
//    private String customerAddress;

    // 추가해야 할 컬럼(결제모듈을 위해서)
    @Column
    private String email;
    @Column
    private String address;
}