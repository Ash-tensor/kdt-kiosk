package ac.su.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomOptionDTO {
    private Long id;
    private String name;
    private Double additionalPrice;
    private String menuName;
}
