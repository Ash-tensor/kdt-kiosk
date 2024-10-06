package ac.su.kiosk.service;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Kiosk;
import ac.su.kiosk.domain.Store;
import ac.su.kiosk.repository.AdminRepository;
import ac.su.kiosk.repository.KioskRepository;
import ac.su.kiosk.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KioskService {
    @Autowired
    private KioskRepository kioskRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Kiosk> findKiosksByAdminId(int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("No admin found with id: " + adminId));

        Optional<Store> storeOptional = storeRepository.findByAdmin(admin);

        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            return kioskRepository.findByStore(store);
        } else {
            throw new IllegalArgumentException("No store found for adminId: " + adminId);
        }
    }

    public Kiosk makeKiosk(Store store) {
        Kiosk kiosk = new Kiosk();
        kiosk.setStore(store);
        String kioskNumber = UUID.randomUUID().toString();
        kiosk.setNumber(kioskNumber); // 이거 일련번호인듯 아마
        return kiosk;
    }

    public List<Kiosk> findKiosksByAdminIdAndStoreId(int adminId, int storeId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("No admin found with id: " + adminId));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("No store found with id: " + storeId));

        if (!store.getAdmin().equals(admin)) {
            throw new IllegalArgumentException("Store does not belong to the given admin.");
        }

        return kioskRepository.findByStore(store);
    }

}
