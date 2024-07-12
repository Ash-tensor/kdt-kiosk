package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.dto.CustomerSetPasswordRequestDTO;
import ac.su.kiosk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    // 입력받은 전화번호와 일치하는 유저가 있는지 확인
    @GetMapping("/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // 전화번호로 유저를 등록하고 비밀번호 설정
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestParam String phone, @RequestParam String password) {
        Customer customer = customerService.createCustomer(phone);
        customerService.setPassword(customer, password);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
