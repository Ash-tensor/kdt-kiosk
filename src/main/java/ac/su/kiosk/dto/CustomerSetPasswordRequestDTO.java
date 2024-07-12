package ac.su.kiosk.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerSetPasswordRequestDTO {
    private String phoneNumber;
    private String password;
}
