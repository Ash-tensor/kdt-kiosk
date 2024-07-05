package ac.su.kiosk.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service

public class StorageService {
    private final Storage storage;
    @Value("${spring.cloud.gcp.storage.bucket-name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            String blobName = file.getOriginalFilename();
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, blobName).build(),
                    file.getBytes()
            );
            return blobInfo.getMediaLink();
        } catch (IOException e) {
            return "실패";
        }
    }
}
