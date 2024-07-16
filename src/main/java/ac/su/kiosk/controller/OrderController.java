package ac.su.kiosk.controller;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.dto.IAMPortDTO;
import ac.su.kiosk.repository.OrderModuleDTORepository;
import ac.su.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderModuleDTORepository orderModuleDTORepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/iamPortDto")
    public void createIamPortDto(@RequestBody IAMPortDTO iamPortDTO) {
        OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(iamPortDTO.getOrderUid()).get();
        orderModuleDTO.setPaymentUid(iamPortDTO.getPaymentUid());
        orderModuleDTO.setPrice(iamPortDTO.getPrice());
        orderModuleDTO.setStatus(PaymentStatus.OK);
        orderModuleDTORepository.save(orderModuleDTO);
    }
}
