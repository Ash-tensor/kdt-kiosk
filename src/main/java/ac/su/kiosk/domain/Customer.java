package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customerID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "customerName")
    private String customerName;

    @Column
    private String customerPhone;

    @Column
    private String customerAddress;
}