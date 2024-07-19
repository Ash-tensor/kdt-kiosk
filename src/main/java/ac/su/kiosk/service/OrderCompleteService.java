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

}
