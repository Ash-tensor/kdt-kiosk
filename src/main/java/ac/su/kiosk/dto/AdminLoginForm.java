package ac.su.kiosk.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdminLoginForm {
    private String name;
    private String password;
}
