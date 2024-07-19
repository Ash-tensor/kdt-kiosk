package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.dto.AdminLoginForm;
import ac.su.kiosk.dto.LoginResponse;
import ac.su.kiosk.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kk/kiosk")
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> validationAdmin(@RequestBody AdminLoginForm request) {
        Optional<Admin> foundOptAdmin = adminService.findAdminByName(request.getName());

        if (foundOptAdmin.isPresent()) {
            Admin foundAdmin = foundOptAdmin.get();
            if (passwordEncoder.matches(request.getPassword(), foundAdmin.getPassword())) {
                LoginResponse response = new LoginResponse("Login successful", foundAdmin.getId());
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUpNewAdmin(@RequestBody Admin request) {
        Optional<Admin> existingAdminByName = adminService.findAdminByName(request.getName());
        if (existingAdminByName.isPresent()) {
            return new ResponseEntity<>("Name already taken", HttpStatus.CONFLICT);
        }

        Optional<Admin> existingAdminByEmail = adminService.findAdminByEmail(request.getEmail());
        if (existingAdminByEmail.isPresent()) {
            return new ResponseEntity<>("Email already used", HttpStatus.CONFLICT);
        }

        Admin newAdmin = new Admin();
        newAdmin.setName(request.getName());
        newAdmin.setPassword(request.getPassword()); // 비밀번호 암호화
        newAdmin.setEmail(request.getEmail());
        adminService.saveAdmin(newAdmin);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }

}
