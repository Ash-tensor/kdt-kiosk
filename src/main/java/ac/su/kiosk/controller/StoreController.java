package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Store;
import ac.su.kiosk.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
