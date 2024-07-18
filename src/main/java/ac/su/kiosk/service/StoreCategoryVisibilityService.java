package ac.su.kiosk.service;

import ac.su.kiosk.domain.StoreCategoryVisibility;
import ac.su.kiosk.repository.StoreCategoryVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCategoryVisibilityService {
    private final StoreCategoryVisibilityRepository repository;

    public List<StoreCategoryVisibility> getVisibilityByStore(Long storeId) {
        return repository.findByStoreId(storeId);
    }

    public StoreCategoryVisibility setVisibility(StoreCategoryVisibility visibility) {
        return repository.save(visibility);
    }
}
