package ac.su.kiosk.service;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Store;
import ac.su.kiosk.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreService {
    StoreRepository storeRepository;
    public Store store(String name, String location, Admin admin) {
        Store store = new Store();
        store.setName(name);
        store.setLocation(location);
        store.setAdmin(admin);
        storeRepository.save(store);
        return store;
    }
}
