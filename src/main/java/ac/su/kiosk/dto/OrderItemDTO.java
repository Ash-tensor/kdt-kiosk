package ac.su.kiosk.dto;

import ac.su.kiosk.domain.CustomOption;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OrderItemDTO {
    private String paymentUid;
    private int menuId;
//    private List<CustomOption> customOptions;
    private int quantity;
    private Long price;
}
