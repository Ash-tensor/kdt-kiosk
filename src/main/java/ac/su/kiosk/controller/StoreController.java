package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Store;
import ac.su.kiosk.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kk/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{adminId}")
    public ResponseEntity<Store> getStoreByAdminId(@PathVariable int adminId) {
        Store store = storeService.findStoreByAdminId(adminId);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/list/{adminId}") // adminId에 해당하는 모든 Store를 반환
    public ResponseEntity<List<Store>> getStoresByAdminId(@PathVariable int adminId) {
        List<Store> stores = storeService.findStoresByAdminId(adminId);
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/all")
    public List<Store> getAllStores() {
        return storeService.findAllStores();
    }
}
