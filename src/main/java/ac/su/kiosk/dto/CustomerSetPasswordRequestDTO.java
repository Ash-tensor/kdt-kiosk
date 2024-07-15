package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSetPasswordRequestDTO {
    private String phoneNumber;
    private String password;
}
