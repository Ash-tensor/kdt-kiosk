package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OrderComplete;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCompleteRepository extends JpaRepository<OrderComplete, Long> {
    List<OrderComplete> findAllByOrderId(Long orderId);

    @Transactional
    @Modifying
    @Query("update OrderComplete oc set oc.complete = true where oc.id = :id")
    void updateById(Long id);

    List<OrderComplete> findAllByComplete(Boolean target);
}
