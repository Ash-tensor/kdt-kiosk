package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuDTO {
    private String name;
    private int categoryId;
    private Long price;
    private boolean soldOut;
    private String tag;
    private String[] options;
}