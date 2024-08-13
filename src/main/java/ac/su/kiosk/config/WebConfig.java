package ac.su.kiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://www.aikiosk.shop") // React 앱의 URL
//                        .allowedOrigins("http://white-faculty-427513-f2.de.r.appspot.com") // React 앱의 URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*") // 모든 헤더를 허용
                        .allowCredentials(true); // 쿠키 및 인증 정보를 허용
            }
        };
    }
}
