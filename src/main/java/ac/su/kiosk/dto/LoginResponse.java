package ac.su.kiosk.dto;

import ac.su.kiosk.jwt.AccessTokenDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private int adminId;
}
