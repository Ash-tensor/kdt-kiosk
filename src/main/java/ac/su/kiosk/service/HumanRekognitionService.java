package ac.su.kiosk.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@RequiredArgsConstructor
@Service
public class HumanRekognitionService {

    @Autowired
    private AmazonRekognition client;

    public DetectFacesResult detectFacesRequest(MultipartFile multipartFile) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes())))
                .withAttributes(Attribute.ALL);

        return client.detectFaces(request);
    }
}
