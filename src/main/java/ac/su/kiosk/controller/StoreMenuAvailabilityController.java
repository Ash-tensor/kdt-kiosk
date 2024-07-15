package ac.su.kiosk.controller;

import ac.su.kiosk.domain.StoreMenuAvailability;
import ac.su.kiosk.service.StoreMenuAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store-menu-availability")
@RequiredArgsConstructor
public class StoreMenuAvailabilityController {
    private final StoreMenuAvailabilityService service;

    @GetMapping("/{storeId}")
    public ResponseEntity<List<StoreMenuAvailability>> getAvailabilityByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(service.getAvailabilityByStore(storeId));
    }

    @PostMapping
    public ResponseEntity<StoreMenuAvailability> setAvailability(@RequestBody StoreMenuAvailability availability) {
        return ResponseEntity.ok(service.setAvailability(availability));
    }
}
