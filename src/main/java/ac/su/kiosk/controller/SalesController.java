package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.repository.OrderRepository;
import ac.su.kiosk.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    //일간 매출 내역->사용자가 요청한 날짜에 해당하는 주문 내역을 데이터베이스에서 조회하여 반환
    @GetMapping("daily")
    public List<Order> getDailySales(@RequestParam String date){
        LocalDateTime startDateTime = LocalDate.parse(date, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1);//startDateTime에 하루를 더해 다음 날의 자정을 endDateTime으로 설정
        return orderRepository.findAllByDateTimeBetween(startDateTime, endDateTime);
        //findAllByODateTimeBetween 메소드는 JPA 리포지토리 메소드로, Order 엔티티의 DateTime 필드가 startDateTime과 endDateTime 사이에 있는 모든 주문을 반환
    }

    //월간 매출 내역
    @GetMapping("/monthly")
    public List<Order> getMonthlySales(@RequestParam String yearMonth) {
        LocalDateTime startDateTime = LocalDate.parse(yearMonth + "-01", DateTimeFormatter.ISO_DATE).atStartOfDay();//yearMonth 문자열에 "-01"을 추가하여 해당 월의 첫날로 변환
        LocalDateTime endDateTime = startDateTime.plusMonths(1);//startDateTime에 한 달을 더해 다음 달의 첫날 자정을 endDateTime으로 설정
        return orderRepository.findAllByDateTimeBetween(startDateTime, endDateTime);
        //findAllByDateTimeBetween 메소드는 JPA 리포지토리 메소드로, Order 엔티티의 DateTime 필드가 startDateTime과 endDateTime 사이에 있는 모든 주문을 반환
    }

    //상세 매출 확인
    @GetMapping("/details")
    public List<Payment> getOrderDetails(@RequestParam Long orderId) {
        return paymentRepository.findAllByOrderId(orderId);
    }
}
