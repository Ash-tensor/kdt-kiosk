package ac.su.kiosk.controller;

import ac.su.kiosk.domain.HumanRekognitionResult;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.service.HumanRekognitionService;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class HumanRekognitionController {

    @Autowired
    HumanRekognitionService humanRekognitionService;

    @PostMapping("/human-rekognition/image/test")
    public ResponseEntity<DetectFacesResult> testDetectFaces(@RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(humanRekognitionService.testDetectFacesRequest(image));
    }

    @PostMapping("/human-rekognition/image")
    public ResponseEntity<Object> detectFaces(@RequestPart MultipartFile image, @RequestPart Order order) throws IOException {
        HumanRekognitionResult humanRekognitionResult =  humanRekognitionService.detectFacesRequest(image, order);
        return ResponseEntity.ok(humanRekognitionResult);
    }
    

    @PostMapping("/human-rekognition/only_image")
    public ResponseEntity<Object> detectFaces(@RequestPart MultipartFile image) throws IOException {
        int age = humanRekognitionService.detectFacesRequest(image);
        Map<String, Object> result = new HashMap<>();
        result.put("age", age);
        return ResponseEntity.ok(result);  // 객체로 반환
    }

}
