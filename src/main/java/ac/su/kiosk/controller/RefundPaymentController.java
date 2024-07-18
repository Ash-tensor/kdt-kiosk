package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.OrderRefundDTO;
import ac.su.kiosk.service.OrderService;
import ac.su.kiosk.service.RefundPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/payment")
public class RefundPaymentController {
    private final RefundPaymentService refundPaymentService;

    @GetMapping("/all")
    public List<OrderRefundDTO> getAllOrders(){
        return refundPaymentService.getAllOrders();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam(value = "id", required = false) Long id) {
        refundPaymentService.deleteOrder(id);
        return ResponseEntity.ok("삭제됨");
    }
}
