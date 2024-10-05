package ac.su.kiosk.service;

import ac.su.kiosk.domain.HumanRekognitionResult;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.repository.HumanRekognitionRepository;
import ac.su.kiosk.repository.OrderRepository;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HumanRekognitionService {

    @Autowired
    private final HumanRekognitionRepository humanRekognitionRepository;
    @Autowired
    private AmazonRekognition client;

    private final OrderRepository orderRepository;

    public DetectFacesResult testDetectFacesRequest(MultipartFile multipartFile) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes())))
                .withAttributes(Attribute.ALL);

        return client.detectFaces(request);
    }

//    public HumanRekognitionResult detectFacesRequest(MultipartFile multipartFile, Order order) throws IOException {
//        DetectFacesRequest request = new DetectFacesRequest()
//                .withImage(new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes())))
//                .withAttributes(Attribute.ALL);
//
//        DetectFacesResult detectFacesResult = client.detectFaces(request);
//        FaceDetail ageRange =  detectFacesResult.getFaceDetails().get(1);
//        FaceDetail gender = detectFacesResult.getFaceDetails().get(4);
//
//        HumanRekognitionResult humanRekognition = new HumanRekognitionResult();
//        humanRekognition.setHigh(ageRange.getAgeRange().getHigh());
//        humanRekognition.setLow(ageRange.getAgeRange().getLow());
//        humanRekognition.setGender(gender.getGender().getValue().equals("Male"));
//        humanRekognition.setOrder(order);
//
//        humanRekognitionRepository.save(humanRekognition);
//
//        return humanRekognition;
//    }

    public int detectFacesRequest(MultipartFile image) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                .withAttributes(Attribute.ALL);

        DetectFacesResult detectFacesResult = client.detectFaces(request);
        List<FaceDetail> faceDetails = detectFacesResult.getFaceDetails();

        HumanRekognitionResult humanRekognition = new HumanRekognitionResult();

        FaceDetail targetFaceDetail = faceDetails.get(0);
        AgeRange ageRange = targetFaceDetail.getAgeRange();
        int high = ageRange.getHigh();
        int low = ageRange.getLow();
        int mean = (high + low) / 2;

        return mean;
    }

    public HumanRekognitionResult detectFacesRequest(MultipartFile multipartFile, Order order) throws IOException {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes())))
                .withAttributes(Attribute.ALL);

        DetectFacesResult detectFacesResult = client.detectFaces(request);
        List<FaceDetail> faceDetails = detectFacesResult.getFaceDetails();

        HumanRekognitionResult humanRekognition = new HumanRekognitionResult();

        FaceDetail targetFaceDetail = faceDetails.get(0);
        AgeRange ageRange = targetFaceDetail.getAgeRange();
        Gender gender = targetFaceDetail.getGender();

        int high = ageRange.getHigh();
        int low = ageRange.getLow();
        humanRekognition.setHigh(high);
        humanRekognition.setLow(low);
        humanRekognition.setGender(gender.getValue().equals("Male"));
        humanRekognition.setOrder(order);
        humanRekognitionRepository.save(humanRekognition);

        return humanRekognition;
    }

    public HumanRekognitionResult getHumanRekognitionResultByOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Order order1 = order.get();
        HumanRekognitionResult result = humanRekognitionRepository.getHumanRekognitionResultByOrder(order1);
        return result;
    }

    public void deleteHumanRekognitionResult(HumanRekognitionResult humanRekognitionResult) {
        humanRekognitionRepository.delete(humanRekognitionResult);
    }

    public List<HumanRekognitionResult> getAllHumanRekognitionResult() {
        return humanRekognitionRepository.findAll();
    }


}
