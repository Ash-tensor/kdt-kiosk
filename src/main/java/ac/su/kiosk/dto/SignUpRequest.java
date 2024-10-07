package ac.su.kiosk.dto;

import ac.su.kiosk.constant.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name; // 사용자 이름
    private String password; // 비밀번호
    private String email; // 이메일
    private String storeName; // 스토어 이름
    private String storeLocation; // 스토어 위치
    private String kioskNumber; // 키오스크 번호
    private Role role; // 역할
}
