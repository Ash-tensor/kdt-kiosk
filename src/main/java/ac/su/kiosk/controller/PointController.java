package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.PointRequestDTO;
import ac.su.kiosk.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/points")
public class PointController {
    @Autowired
    private PointService pointService;

    @PostMapping("/add")
    public ResponseEntity<String> addPoints(@RequestBody PointRequestDTO request) {
        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean isSuccess = pointService.addPoints(request.getCustomerPhone(), order);
        return isSuccess ? ResponseEntity.ok("포인트가 성공적으로 적립되었습니다.") : ResponseEntity.internalServerError().build();
    }

    @PostMapping("/use")
    public ResponseEntity<String> usePoints(@RequestBody PointRequestDTO request) {
        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean success = pointService.usePoints(request.getCustomerPhone(), request.getPointsToUse(), order);
        if (success) {
            return ResponseEntity.ok("포인트가 성공적으로 사용되었습니다.");
        } else {
            return ResponseEntity.status(400).body("불충분한 포인트");
        }
    }
}
