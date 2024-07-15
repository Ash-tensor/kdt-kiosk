package ac.su.kiosk.dto;

import ac.su.kiosk.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerPointResponseDTO {
    private boolean valid;
    private int points;
    private Customer customer; // 추가된 고객 정보
}
