package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController// JSON, XML 등 뷰가 아닌 데이터를 직접 반환->@Controller와 @ResponseBody를 합친 것과 같은 역할
@RequestMapping("/points")
public class PointController {
    @Autowired// Spring 컨테이너에서 PointService 타입의 빈(bean)을 자동으로 주입->PointService 객체를 사용가능함
    private PointService pointService;

    @PostMapping("/add")
    public ResponseEntity<String> addPoints(@RequestParam String customerPhone, @RequestParam int totalPrice){//요청 파라미터로 전달받은
        Order order = new Order();
        order.setOrderDateTime(LocalDateTime.now());
        order.setTotalPrice(totalPrice);

        boolean isSuccess = pointService.addPoints(customerPhone, order);
        return isSuccess ? ResponseEntity.ok("포인트가 성공적으로 적립되었습니다.") : ResponseEntity.internalServerError().build();
    }

    @PostMapping("/use")
    public ResponseEntity<String> usePoints(@RequestParam String customerPhone, @RequestParam int pointsToUse, @RequestParam int totalPrice){
        Order order = new Order();
        order.setOrderDateTime(LocalDateTime.now());
        order.setTotalPrice(totalPrice);

        boolean success = pointService.usePoints(customerPhone, pointsToUse, order);
        if(success){
            return ResponseEntity.ok("포인트가 성공적으로 사용되었습니다.");
        }else{
            return ResponseEntity.status(400).body("불충분한 포인트");
        }
    }
}
