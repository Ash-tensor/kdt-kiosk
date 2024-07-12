package ac.su.kiosk.service;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private BCryptPasswordEncoder passwordEncoder;

    // 전화번호로 고객을 조회하는 메서드
    public Optional<Customer> getCustomerByPhone(String customerPhone) {
        return customerRepository.findByPhoneNumber(customerPhone);
    }

    // 새로운 고객을 생성하는 메서드
    public Customer createCustomer(String customerPhone) {
        Customer customer = new Customer();
        customer.setAddress("dummy address");
        customer.setName("dummy customer");
        customer.setEmail("dummy@email.com");
        customer.setPhoneNumber(customerPhone);
        customer.setPassword(passwordEncoder.encode("1111")); // 비밀번호 해시화하여 저장
        customer.setPoints(0);
        return customerRepository.save(customer);
    }

    // 고객이 존재하지 않으면 새로운 고객을 생성하는 메서드
    public Customer getOrCreateCustomer(String customerPhone) {
        return getCustomerByPhone(customerPhone).orElseGet(() -> createCustomer(customerPhone));
    }

    public void setPassword(Customer customer, String password) {
        customer.setPassword(passwordEncoder.encode(password)); // 비밀번호 해시화하여 저장
        customerRepository.save(customer);
    }
}
