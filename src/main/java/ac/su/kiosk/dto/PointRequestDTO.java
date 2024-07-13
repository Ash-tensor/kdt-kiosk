package ac.su.kiosk.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PointRequestDTO {
    private String phoneNumber;
    private int totalPrice;
    private int pointsToUse; // `usePoints` 요청에만 사용됩니다.
}
