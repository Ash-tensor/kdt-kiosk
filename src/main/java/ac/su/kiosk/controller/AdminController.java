package ac.su.kiosk.controller;

import ac.su.kiosk.domain.AdminCreateForm;
import ac.su.kiosk.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/signup")
    public String signup(AdminCreateForm userCreateForm) {
        return "signup_form";
    }
    @PostMapping("/signup")
    public String signup(@Valid AdminCreateForm adminCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!adminCreateForm.getPassword1().equals(adminCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            adminService.create(
                    adminCreateForm.getAdminName(),
                    adminCreateForm.getPassword1()
            );
        } catch (IllegalStateException e) {
            bindingResult.reject(
                    "signupFailed",
                    "이미 등록된 관리자 입니다."
            );
            return "signup_form";
        } catch (Exception e) {
            bindingResult.reject(
                    "signupFailed",
                    e.getMessage()
            );
            return "signup_form";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login_form";
    }
}
