package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, QuerydslPredicateExecutor<Customer> {
    Optional<Customer> findByPhoneNumber(String customerPhone);
}
