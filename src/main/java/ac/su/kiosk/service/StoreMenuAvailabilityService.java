package ac.su.kiosk.service;

import ac.su.kiosk.domain.StoreMenuAvailability;
import ac.su.kiosk.repository.StoreMenuAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuAvailabilityService {
    private final StoreMenuAvailabilityRepository repository;

    public List<StoreMenuAvailability> getAvailabilityByStore(Long storeId) {
        return repository.findByStoreId(storeId);
    }

    public StoreMenuAvailability setAvailability(StoreMenuAvailability availability) {
        return repository.save(availability);
    }
}
