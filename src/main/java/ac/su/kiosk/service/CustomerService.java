package ac.su.kiosk.service;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    // 전화번호로 고객을 조회하는 메서드
    public Optional<Customer> getCustomerByPhone(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    // 새로운 고객을 생성하는 메서드
    public Customer createCustomer(String phoneNumber, String password) {
        Customer customer = new Customer();
        customer.setAddress("dummy address");
        customer.setName("dummy customer");
        customer.setEmail("dummy@email.com");
        customer.setPhoneNumber(phoneNumber);
        customer.setPassword(passwordEncoder.encode(password)); // 비밀번호 해시화하여 저장
        customer.setPoints(0);
        return customerRepository.save(customer);
    }

    public boolean validatePassword(String phoneNumber, String password) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer.isPresent()) {
            return passwordEncoder.matches(password, customer.get().getPassword());
        }
        return false;
    }

    public int getPoints(String phoneNumber) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
        return customer.map(Customer::getPoints).orElse(0);
    }

//    // 고객이 존재하지 않으면 새로운 고객을 생성하는 메서드
//    public Customer getOrCreateCustomer(String phoneNumber) {
//        return getCustomerByPhone(phoneNumber).orElseGet(() -> createCustomer(phoneNumber, "1111"));
//    }

//    public void setPassword(Customer customer, String password) {
//        customer.setPassword(passwordEncoder.encode(password)); // 비밀번호 해시화하여 저장
//        customerRepository.save(customer);
//    }
}
