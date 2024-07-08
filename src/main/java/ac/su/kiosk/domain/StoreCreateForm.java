package ac.su.kiosk.domain;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateForm {
    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;
    @NotEmpty(message = "위치는 필수항목입니다.")
    private String location;
}
