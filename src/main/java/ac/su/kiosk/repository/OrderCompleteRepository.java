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
    void updateTrueById(Long id);

    @Query("select " +
            "oc from OrderComplete oc " +
            "JOIN FETCH oc.order o " +
            "JOIN FETCH o.orderModuleDTO omd " +
            "where oc.complete = :target")
    List<OrderComplete> findAllByComplete(Boolean target);

//    @Query("select oc from OrderComplete oc JOIN FETCH oc.order where oc.complete = :target")
//    List<OrderComplete> findAllByCompleteTest(Boolean target);
}
