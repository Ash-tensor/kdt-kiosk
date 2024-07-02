package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentID(Long paymentID);
}
