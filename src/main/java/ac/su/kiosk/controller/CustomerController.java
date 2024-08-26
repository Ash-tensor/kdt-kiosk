package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.dto.CustomerPointResponseDTO;
import ac.su.kiosk.dto.CustomerSetPasswordRequestDTO;
import ac.su.kiosk.dto.PointRequestDTO;
import ac.su.kiosk.service.CustomerService;
import ac.su.kiosk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping("/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerSetPasswordRequestDTO request) {
        Customer customer = customerService.createCustomer(request.getPhoneNumber(), request.getPassword());
        userService.saveCustomerUser(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/validatePassword")
    public ResponseEntity<CustomerPointResponseDTO> validatePassword(@RequestBody CustomerSetPasswordRequestDTO request) {
        boolean isValid = customerService.validatePassword(request.getPhoneNumber(), request.getPassword());
        int points = isValid ? customerService.getPoints(request.getPhoneNumber()) : 0;
        Customer customer = isValid ? customerService.getCustomerByPhone(request.getPhoneNumber()).orElse(null) : null;
        return ResponseEntity.ok(new CustomerPointResponseDTO(isValid, points, customer));
    }

    @PostMapping("/addPoints")
    public ResponseEntity<String> addPoints(@RequestBody PointRequestDTO request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("전화번호가 제공되지 않았습니다.");
        }

        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean isSuccess = customerService.addPoints(request.getPhoneNumber(), order);
        return isSuccess ? ResponseEntity.ok("포인트가 성공적으로 적립되었습니다.") : ResponseEntity.internalServerError().build();
    }

    @PostMapping("/usePoints")
    public ResponseEntity<String> usePoints(@RequestBody PointRequestDTO request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("전화번호가 제공되지 않았습니다.");
        }

        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setTotalPrice(request.getTotalPrice());

        boolean success = customerService.usePoints(request.getPhoneNumber(), request.getPointsToUse(), order);
        if (success) {
            return ResponseEntity.ok("포인트가 성공적으로 사용되었습니다.");
        } else {
            return ResponseEntity.status(400).body("불충분한 포인트");
        }
    }
}
