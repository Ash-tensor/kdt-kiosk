package ac.su.kiosk.service;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointService {
    //포인트 적립 및 사용 규칙 설정
    //(>)포인트 적립: 결제 금액의 1%를 포인트로 적립
    //(>)포인트 사용: 적립된 포인트를 결제 금액에서 1포인트 = 1원의 비율로 차감
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    //포인트 적립
    public boolean addPoints(String customerPhone, Order order) {
        Optional<Customer> optionalCustomer = customerService.getCustomerByPhone(customerPhone);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            long points = calculatePoints(order.getTotalPrice());  // 1% 계산
            customer.setPoints((int) (customer.getPoints() + points));  // point 필드에 할당
            customerRepository.save(customer); // 저장
            customerRepository.flush();
            customer = customerRepository.findById(customer.getId()).orElseThrow();  // Optional에서 고객 다시 가져오기
            System.out.println("Customer Points after adding: " + customer.getPoints());  // 디버깅 출력
            return true;
        } else {
            System.out.println("Customer not found for phone number: " + customerPhone);
            return false;
        }
    }

    //포인트 사용
    public boolean usePoints(String customerPhone, int pointsToUse, Order order) {
        Optional<Customer> optionalCustomer = customerService.getCustomerByPhone(customerPhone);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getPoints() >= pointsToUse) {
                customer.setPoints(customer.getPoints() - pointsToUse);
                long remainingAmount = order.getTotalPrice() - pointsToUse;
                customerRepository.save(customer);
                return remainingAmount >= 0;
            } else {
                System.out.println("Insufficient points for customer: " + customerPhone);
                return false;
            }
        } else {
            System.out.println("Customer not found for phone number: " + customerPhone);
            return false;
        }
    }

    private long calculatePoints(long totalPrice) {
        return (long) (totalPrice * 0.01); // 1% 적립
    }
}
