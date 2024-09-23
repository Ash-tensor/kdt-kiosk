package ac.su.kiosk.controller.siren;

import ac.su.kiosk.dto.siren.SirenUserLocationDTO;
import ac.su.kiosk.service.siren.SirenUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/siren-user/location")
@RequiredArgsConstructor
public class SirenUserLocationController {
    private final SirenUserService sirenUserService;

    @PostMapping("/renew")
    public ResponseEntity<String> renewUserLocation(@RequestBody SirenUserLocationDTO sirenUserLocationDTO) {

        Long userID = sirenUserLocationDTO.getId();
        String address = sirenUserLocationDTO.getAddress();
        Double latitude = sirenUserLocationDTO.getLatitude();
        Double longitude = sirenUserLocationDTO.getLongitude();
        try {
            sirenUserService.updateSirenUserLocation(userID, address, latitude, longitude);

            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

}
