package ac.su.kiosk.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    String apiKey = "3138755861825685";
    String secretKey = "r8nLkhIcnS0ml1IUnbWIDRklNnoUWMpMmsjQmeU7jbfAlDrnKEjKSZ5vaxGajaKRLMx8N2eH7C9ujrtL";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
