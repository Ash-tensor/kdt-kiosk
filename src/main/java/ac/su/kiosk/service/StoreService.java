package ac.su.kiosk.service;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Store;
import ac.su.kiosk.repository.AdminRepository;
import ac.su.kiosk.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final AdminRepository adminRepository;

    public Store findStoreByAdminId(int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("No admin found with id: " + adminId));
        return storeRepository.findByAdmin(admin)
                .orElseThrow(() -> new IllegalArgumentException("No store found for adminId: " + adminId));
    }

    public List<Store> findStoresByAdminId(int adminId) {
        return storeRepository.findAllByAdminId(adminId); // adminId로 모든 스토어 반환
    }

    public Store store(String name, String location, Admin admin) {
        Store store = new Store();
        store.setName(name);
        store.setLocation(location);
        store.setAdmin(admin);
        storeRepository.save(store);
        return store;
    }
}
