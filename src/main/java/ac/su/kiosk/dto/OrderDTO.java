package ac.su.kiosk.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class OrderDTO {
    private int customerId;
    private int kioskId;
    private LocalDateTime datetime;
    private long totalPrice;
    private boolean isPackaged;
    private String paymentUid;

    public OrderDTO(LocalDateTime datetime, long amount){
        this.datetime = datetime;
        this.totalPrice = amount;
    }
}
