package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("iap")
public class IAmPortProperty {
    private String apiKey;
    private String secretKey;
}
