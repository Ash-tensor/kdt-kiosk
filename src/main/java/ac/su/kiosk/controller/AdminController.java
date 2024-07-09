package ac.su.kiosk.controller;

import ac.su.kiosk.domain.AdminCreateForm;
import ac.su.kiosk.domain.AdminLoginForm;
import ac.su.kiosk.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//@Controller
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping ("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody AdminCreateForm adminCreateForm, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        if (!adminCreateForm.getPassword1().equals(adminCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            adminService.create(
                    adminCreateForm.getAdminName(),
                    adminCreateForm.getAdminEmail(),
                    adminCreateForm.getPassword1()
            );
        } catch (IllegalStateException e) {
            bindingResult.reject(
                    "signupFailed",
                    "이미 등록된 관리자 입니다."
            );
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } catch (Exception e) {
            bindingResult.reject(
                    "signupFailed",
                    e.getMessage()
            );
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok("Admin registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginForm adminLoginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        boolean isAuthenticated = adminService.authenticate(adminLoginForm.getAdminEmail(), adminLoginForm.getPassword());
        if (isAuthenticated) {
            String token = adminService.generateToken(adminLoginForm.getAdminEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
//    @GetMapping("/signup")
//    public String signup(AdminCreateForm userCreateForm) {
//        return "signup_form";
//    }
//    @PostMapping("/signup")
//    public String signup(@Valid AdminCreateForm adminCreateForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "signup_form";
//        }
//
//        if (!adminCreateForm.getPassword1().equals(adminCreateForm.getPassword2())) {
//            bindingResult.rejectValue("password2", "passwordInCorrect",
//                    "2개의 패스워드가 일치하지 않습니다.");
//            return "signup_form";
//        }
//
//        try {
//            adminService.create(
//                    adminCreateForm.getAdminName(),
//                    adminCreateForm.getAdminEmail(),
//                    adminCreateForm.getPassword1()
//
//            );
//        } catch (IllegalStateException e) {
//            bindingResult.reject(
//                    "signupFailed",
//                    "이미 등록된 관리자 입니다."
//            );
//            return "signup_form";
//        } catch (Exception e) {
//            bindingResult.reject(
//                    "signupFailed",
//                    e.getMessage()
//            );
//            return "signup_form";
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "admin/login_form";
//    }
}
