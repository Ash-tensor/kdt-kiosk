package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private  Long id;
    private LocalDateTime datetime;
    private Long amount;

    public OrderDTO(Long id, LocalDateTime datetime, Long amount){
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
    }
}
