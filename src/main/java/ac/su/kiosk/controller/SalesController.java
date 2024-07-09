package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.Payment;
import ac.su.kiosk.dto.OrderDTO;
import ac.su.kiosk.dto.PaymentDTO;
import ac.su.kiosk.repository.OrderRepository;
import ac.su.kiosk.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    //일간 매출 내역->사용자가 요청한 날짜에 해당하는 주문 내역을 데이터베이스에서 조회하여 반환
    @GetMapping("daily")
    public ResponseEntity<List<OrderDTO>> getDailySales(@RequestParam String date){
        LocalDateTime startDateTime = LocalDate.parse(date, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1);
        List<Order> orders = orderRepository.findAllByDateTimeBetween(startDateTime, endDateTime);//OrderRepository를 사용하여 startDateTime과 endDateTime 사이에 있는 모든 주문을 조회

        List<OrderDTO> orderDTOs = orders.stream().map(this::convertToOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);//변환된 OrderDTO 리스트를 HTTP 응답 본문으로 설정하고, 200 OK 상태 코드와 함께 반환
    }

    //월간 매출 내역
    @GetMapping("/monthly")
    public ResponseEntity<List<OrderDTO>> getMonthlySales(@RequestParam String yearMonth) {
        LocalDateTime startDateTime = LocalDate.parse(yearMonth + "-01", DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusMonths(1);
        List<Order> orders = orderRepository.findAllByDateTimeBetween(startDateTime, endDateTime);

        List<OrderDTO> orderDTOs = orders.stream().map(this::convertToOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    //상세 매출 확인
    @GetMapping("/details")
    public ResponseEntity<List<PaymentDTO>> getOrderDetails(@RequestParam Long orderId) {
        List<Payment> payments = paymentRepository.findAllByOrderId(orderId);
        if (payments.isEmpty()) {//조회된 결제 내역 리스트가 비어 있는지 확인합니다. 비어 있다면, 해당 주문 ID에 대한 결제 내역이 없다는 것을 의미
            return ResponseEntity.notFound().build();//결제 내역이 없을 경우, 404 Not Found 상태 코드와 함께 응답을 반환
        }

        List<PaymentDTO> paymentDTOs = payments.stream().map(this::convertToPaymentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(paymentDTOs);
    }

    // Order 엔티티를 OrderDTO로 변환하는 메소드
    private OrderDTO convertToOrderDTO(Order order) {
        return new OrderDTO(order.getId(), order.getDateTime(), order.getTotalPrice());
    }

    // Payment 엔티티를 PaymentDTO로 변환하는 메소드
    private PaymentDTO convertToPaymentDTO(Payment payment) {
        return new PaymentDTO(payment.getId(), payment.getAmount(), payment.getDateTime());
    }
}
