//package ac.su.kiosk.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//import java.util.List;
//
//@Configuration
//public class GCPConfig {
//    @Bean
//    public Storage storage() throws IOException {
//        // 프로젝트 내부의 서비스 계정 키 파일 경로를 지정합니다.
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("gcp-key.json").getInputStream())
//                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
//        // 인증 정보를 사용하여 Storage 객체를 생성합니다.
//        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//    }
//}

package ac.su.kiosk.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class GCPConfig {
    private static final Logger logger = LoggerFactory.getLogger(GCPConfig.class);

    @Bean
    public Storage storage() throws IOException {
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        if (credentialsPath == null) {
            throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS environment variable is not set.");
        }

        logger.info("Using GOOGLE_APPLICATION_CREDENTIALS from: " + credentialsPath);

        try (FileInputStream credentialsStream = new FileInputStream(credentialsPath)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
            return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            logger.error("Failed to load GoogleCredentials from path: " + credentialsPath, e);
            throw e;
        }
    }
}
