package ac.su.kiosk.service;

import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.repository.TestRepo;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepo testRepo;
    private final Storage storage;
    @Value("${spring.cloud.gcp.storage.bucket-name}")
    private String bucketName;

    public String test(Long id) {
        Optional<TestEntity> temp = testRepo.findById(id);
        TestEntity temp2 = temp.get();
        return temp2.testString;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            String blobName = file.getOriginalFilename();
            BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, blobName).build(),
                file.getBytes()
        );
        return "성공" + blobInfo.getMediaLink();
    } catch (IOException e) {
        return "실패";
    }
}
}
