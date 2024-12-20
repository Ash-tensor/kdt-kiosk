package ac.su.kiosk.controller;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.*;
import ac.su.kiosk.dto.IAMPortDTO;
import ac.su.kiosk.dto.OrderDTO;
import ac.su.kiosk.dto.OrderStatisticDTO;
import ac.su.kiosk.jwt.AccessTokenDTO;
import ac.su.kiosk.jwt.JwtProvider;
import ac.su.kiosk.logDto.OrderRequest;
import ac.su.kiosk.repository.CustomerRepository;
import ac.su.kiosk.repository.KioskRepository;
import ac.su.kiosk.repository.OrderModuleDTORepository;
import ac.su.kiosk.repository.OrderRepository;
import ac.su.kiosk.service.OrderCompleteService;
import ac.su.kiosk.service.OrderService;
import ac.su.kiosk.service.OrderStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderModuleDTORepository orderModuleDTORepository;
    private final CustomerRepository customerRepository;
    private final KioskRepository kioskRepository;
    private final OrderRepository orderRepository;
    private final JwtProvider jwtProvider;

    private final OrderStatisticService orderStatisticService;
    private final OrderCompleteService orderCompleteService;


    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderDTO orderDTO) {
        Order savedOrder = new Order();
        String accessTokenDTO;
        User user = new User();
        if (token != null && token.startsWith("Bearer")) {
            // React에서 요청 Header를 "Bearer 토큰" 형식으로 보냄
            // 접두사(Bearer) 제거
            // = Claim 에 해당하는 String 을 리턴
            accessTokenDTO = token.substring(7);
            user = jwtProvider.getUser(accessTokenDTO);
        }

        Optional<Customer> customerOptional = customerRepository.findById(orderDTO.getCustomerId());
        if(customerOptional.isPresent()) {
            savedOrder.setCustomer(customerOptional.get());
        }
        Optional<Kiosk> kioskOptional = kioskRepository.findById(user.getKiosk().getId());
        if(kioskOptional.isPresent()) {
            savedOrder.setKiosk(kioskOptional.get());
        }
        savedOrder.setDateTime(orderDTO.getDatetime());
        savedOrder.setTotalPrice(orderDTO.getTotalPrice());
        savedOrder.setPackaged(orderDTO.isPackaged());
        savedOrder.setPaymentUid(orderDTO.getPaymentUid());
        Optional<OrderModuleDTO> orderModuleDTOOptional = orderModuleDTORepository.findByPaymentUid(orderDTO.getPaymentUid());
        if(orderModuleDTOOptional.isPresent()) {
            savedOrder.setOrderModuleDTO(orderModuleDTOOptional.get());
        }

        orderRepository.save(savedOrder);


        // ash - ordercomplete를 추가하기 위해 로직을 추가합니다. // 7.19일
        OrderComplete orderComplete = new OrderComplete();
        orderComplete.setComplete(false);
        orderComplete.setOrder(savedOrder);
        orderCompleteService.saveOrderComplete(orderComplete);
        // ordercomplete를 저장하기 위한 로직

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

    @PostMapping("/createOrderRequest")
    public void createOrderRequest(@RequestBody OrderRequest orderRequest) {
        orderService.convertToOrderModuleDTO(orderRequest);
    }

    @GetMapping("/humanrekognition")
    public List<OrderStatisticDTO> getOrderItemsWithHumanRekognition() {
        return orderStatisticService.getOrderItemsWithHumanRekognition();
    }
}