package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Kiosk;
import ac.su.kiosk.service.KioskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kiosks")
public class KioskController {

    private final KioskService kioskService;

    @GetMapping("/{adminId}/kiosks")
    public ResponseEntity<List<Kiosk>> getKiosksByAdminId(@PathVariable int adminId) {
        try {
            List<Kiosk> kiosks = kioskService.findKiosksByAdminId(adminId);
            return ResponseEntity.ok(kiosks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
