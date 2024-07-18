package ac.su.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IAMPortDTO {
    private Long price;
    private String paymentUid;
    private String orderUid;
}