package ac.su.kiosk.config;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class GCPConfig {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${gcp.secret.name}")
    private String secretName;

    @Bean
    public String getGcpCredentials() throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretName, "latest");
            AccessSecretVersionRequest request = AccessSecretVersionRequest.newBuilder()
                    .setName(secretVersionName.toString())
                    .build();
            String secretData = client.accessSecretVersion(request).getPayload().getData().toStringUtf8();

            // Create a temporary file to store the credentials
            Path tempPath = Files.createTempFile("gcp-credentials", ".json");
            try (FileOutputStream fos = new FileOutputStream(tempPath.toFile())) {
                fos.write(secretData.getBytes());
            }

            return tempPath.toString();
        }
    }
}
