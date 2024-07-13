package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.PointRequestDTO;
import ac.su.kiosk.service.CustomerService;
import ac.su.kiosk.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/points")
public class PointController {
    private PointService pointService;
    private CustomerService customerService;

    // 전화번호로 유저 포인트 조회
    @GetMapping("/{phone}/points")
    public ResponseEntity<Integer> getCustomerPoints(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone)
                .map(customer -> ResponseEntity.ok(customer.getPoints()))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPoints(@RequestBody PointRequestDTO request) {
        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean isSuccess = pointService.addPoints(request.getPhoneNumber(), order);
        return isSuccess ? ResponseEntity.ok("포인트가 성공적으로 적립되었습니다.") : ResponseEntity.internalServerError().build();
    }

    @PostMapping("/use")
    public ResponseEntity<String> usePoints(@RequestBody PointRequestDTO request) {
        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean success = pointService.usePoints(request.getPhoneNumber(), request.getPointsToUse(), order);
        if (success) {
            return ResponseEntity.ok("포인트가 성공적으로 사용되었습니다.");
        } else {
            return ResponseEntity.status(400).body("불충분한 포인트");
        }
    }


}
