package ac.su.kiosk.service;

import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    //포인트 적립 및 사용 규칙 설정
    //(>)포인트 적립: 결제 금액의 1%를 포인트로 적립
    //(>)포인트 사용: 적립된 포인트를 결제 금액에서 1포인트 = 1원의 비율로 차감
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getOrCreateCustomer(String customerPhone){
      return customerRepository.findByCustomerPhone(customerPhone).orElseGet(()->{
          Customer customer = new Customer();
          customer.setCustomerPhone(customerPhone);
          customer.setPoints(0);
          return customerRepository.save(customer);
      }); //고객이 존재하지 않으면 새로운 고객을 생성
    }

    //포인트 적립
    public boolean addPoints(String customerPhone, Order order){//*purechaseAmount:총 금액
        Customer customer = getOrCreateCustomer(customerPhone);
        long points = calculatePoints(order.getTotalPrice());  // 1% 계산
        customer.setPoints((int) (customer.getPoints()+points));  // point 필드에 할당
        customerRepository.save(customer);//저장
        customerRepository.flush();
        customer = customerRepository.findById(customer.getCustomerID()).get();
        System.out.println("Customer Points after adding: " + customer.getPoints());  // 디버깅 출력
        return true;
    }

    //포인트 사용
    public  Boolean usePoints(String customerPhone, int pointsToUse, Order order){
        Customer customer = getOrCreateCustomer(customerPhone);
        if(customer.getPoints() >= pointsToUse){
            customer.setPoints(customer.getPoints() - pointsToUse);
            int remainingAmount = (int) (order.getTotalPrice() - pointsToUse);
            customerRepository.save(customer);
            return remainingAmount >= 0;
        } else{
            return false;
        }
    }

    private long calculatePoints(long totalPrice) {
        return (long) (totalPrice * 0.01); // 1% 적립
    }
}
