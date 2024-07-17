package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MenuPictureDTO {

    private String name;
    private int categoryId;
    private Long price;
    private boolean soldOut;
    private String tag;
    private MultipartFile image;
    private String[] options;
}