package ac.su.kiosk.dto.siren;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SirenUserLocationDTO {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
}
