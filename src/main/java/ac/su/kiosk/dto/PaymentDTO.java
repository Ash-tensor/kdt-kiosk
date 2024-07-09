package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private Long amount;
    private Date dateTime;

    public PaymentDTO(Long id, Long amount, Date dateTime){
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
    }
}

