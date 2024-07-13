package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.dto.CustomerPointResponseDTO;
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

    @GetMapping("/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerSetPasswordRequestDTO request) {
        Customer customer = customerService.createCustomer(request.getPhoneNumber(), request.getPassword());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/validatePassword")
    public ResponseEntity<CustomerPointResponseDTO> validatePassword(@RequestBody CustomerSetPasswordRequestDTO request) {
        boolean isValid = customerService.validatePassword(request.getPhoneNumber(), request.getPassword());
        int points = isValid ? customerService.getPoints(request.getPhoneNumber()) : 0;
        Customer customer = isValid ? customerService.getCustomerByPhone(request.getPhoneNumber()).orElse(null) : null;
        return ResponseEntity.ok(new CustomerPointResponseDTO(isValid, points, customer));
    }
}
