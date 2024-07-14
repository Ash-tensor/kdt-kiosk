package ac.su.kiosk.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomOptionRequest {
    private String name;
    private Double additionalPrice;
    private Integer menuId;
}
