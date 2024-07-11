package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.dto.AdminLoginForm;
import ac.su.kiosk.service.AdminService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kk/kiosk")
public class AdminController {
    private final AdminService adminService;

    // 스프링 시큐리티에서 제공하는 비밀번호 암호화 객체
    private final PasswordEncoder passwordEncoder;

    //  로그인 요청 처리
    @PostMapping("/login")
    public ResponseEntity<String> validationAdmin(@RequestBody AdminLoginForm request) {
        // 어드민이 입력한 아이디를 디비에서 검색하여 찾는다.
        Optional<Admin> foundOptAdmin = adminService.findAdminByName(request.getName());

        // 입력한 아이디가 존재 할 시 비밀번호 비교를 한다
        if (foundOptAdmin.isPresent()) {
            // 옵셔널을 벗긴다
            Admin foundAdmin = foundOptAdmin.get();
            // 저장된 비밀번호 암호를 해독하고 비교한다.
            if (passwordEncoder.matches(request.getPassword(), foundAdmin.getPassword())) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
        }
    }

    // 회원가입 요청 처리
    @PostMapping("/sign_up")
    public ResponseEntity<String> signUpNewAdmin(@RequestBody Admin request) {
       // 이름 중복 검사
        Optional<Admin> existingAdminByName = adminService.findAdminByName(request.getName());
        if (existingAdminByName.isPresent()) {
            return new ResponseEntity<>("Name already taken", HttpStatus.CONFLICT);
        }

       // 이메일 중복 검사
        Optional<Admin> existingAdminByEmail = adminService.findAdminByEmail(request.getEmail());
        if (existingAdminByEmail.isPresent()) {
            return new ResponseEntity<>("Email already used", HttpStatus.CONFLICT);
        }

//        if (request.getPassword().length() < 3) {
//            return new ResponseEntity<>("Password must be at least 3 characters long", HttpStatus.BAD_REQUEST);
//        }

        // 어드민 생성
        Admin newAdmin = new Admin();
        newAdmin.setName(request.getName());
        newAdmin.setPassword(request.getPassword());
        newAdmin.setEmail(request.getEmail());
        adminService.saveAdmin(newAdmin);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }
}
