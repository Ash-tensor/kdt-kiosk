package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OrderModuleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderModuleDTORepository extends JpaRepository<OrderModuleDTO, Long> {
    //public OrderModuleDTO findByOrderUid
    Optional<OrderModuleDTO> findByOrderUid(String orderUid);
    List<OrderModuleDTO> findByGpt(Boolean gpt);
    Optional<OrderModuleDTO> findByPaymentUid(String paymentUid);

}
