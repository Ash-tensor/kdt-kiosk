package ac.su.kiosk.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "human_rekognition")
public class HumanRekognitionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int low;
    private int high;
    private boolean gender;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OrderId")
    private Order order;



}
