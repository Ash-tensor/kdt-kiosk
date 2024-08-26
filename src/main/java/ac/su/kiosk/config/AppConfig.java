package ac.su.kiosk.config;

import ac.su.kiosk.dto.IAmPortProperty;
import com.siot.IamportRestClient.IamportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final IAmPortProperty iAmPortProperty;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(iAmPortProperty.getApiKey(), iAmPortProperty.getSecretKey());
    }
}
