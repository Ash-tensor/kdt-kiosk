package ac.su.kiosk.service;

import ac.su.kiosk.domain.OrderComplete;
import ac.su.kiosk.repository.OrderCompleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderCompleteService {
    private final OrderCompleteRepository orderCompleteRepository;

    public void saveOrderComplete(OrderComplete orderComplete) {
        orderCompleteRepository.save(orderComplete);
    }

    public void deleteOrderComplete(Long id) {
        // orderid를 이용해서 삭제할 수 있도록 수정
        orderCompleteRepository.deleteAll(orderCompleteRepository.findAllByOrderId(id));
    }

    public void completeOrder(long id) {
        orderCompleteRepository.updateTrueById(id);
    }
}
