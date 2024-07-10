package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.dto.AdminLoginForm;
import ac.su.kiosk.service.AdminService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/api/kk/kiosk/login")
    public ResponseEntity<AdminLoginForm> validationAdmin(@RequestBody AdminLoginForm request) {
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
