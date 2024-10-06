package ac.su.kiosk.controller;

import ac.su.kiosk.constant.Role;
import ac.su.kiosk.domain.*;
import ac.su.kiosk.dto.AdminLoginForm;
import ac.su.kiosk.dto.LoginResponse;
import ac.su.kiosk.dto.SignUpRequest;
import ac.su.kiosk.jwt.AccessTokenDTO;
import ac.su.kiosk.jwt.JwtProvider;
import ac.su.kiosk.repository.KioskRepository;
import ac.su.kiosk.repository.RefreshTokenRepository;
import ac.su.kiosk.repository.StoreRepository;
import ac.su.kiosk.service.AdminService;
import ac.su.kiosk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kk/kiosk")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StoreRepository storeRepository;
    private final KioskRepository kioskRepository;


/*    @PostMapping("/login")
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
    }*/
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> validationAdmin(@RequestBody AdminLoginForm request) {
        Optional<User> foundOptUser = userService.findByName(request.getName());
        if (foundOptUser.isPresent()) {
            User foundUser = foundOptUser.get();
            if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
                // AccessToken
                String accessToken = jwtProvider.generateToken(foundUser, Duration.ofHours(1L));
                String tokenType = "Bearer";
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO(accessToken, tokenType);

                // RefreshToken
                String refreshToken = jwtProvider.generateToken(foundUser, Duration.ofHours(3L));
                refreshTokenRepository.save(new RefreshToken(refreshToken));

                // React에 전달 할 때 토큰의 값만 전송
                LoginResponse response = new LoginResponse(accessTokenDTO.getAccessToken(), refreshToken, foundUser.getId());
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
        Admin admin = adminService.saveAdmin(newAdmin);

        userService.saveAdminUser(admin);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/sign_up2")
    public ResponseEntity<String> signUpNewUser(@RequestBody SignUpRequest request) {
        // 사용자 이름 중복 체크
        Optional<User> existingUserByName = userService.findByName(request.getName());
        if (existingUserByName.isPresent()) {
            return new ResponseEntity<>("Name already taken", HttpStatus.CONFLICT);
        }

        // 새로운 Admin 생성(SignUp 재사용)
        Admin newAdmin = new Admin();
        newAdmin.setName(request.getName());
        newAdmin.setPassword(request.getPassword());
        newAdmin.setEmail(request.getEmail());
        Admin savedAdmin = adminService.saveAdmin(newAdmin); // Admin 저장

        // 새로운 스토어 생성
        Store store = new Store();
        store.setName(request.getStoreName());
        store.setLocation(request.getStoreLocation());
        store.setAdmin(savedAdmin); // 새로 저장한 Admin 할당

        // 새로운 키오스크 생성
        Kiosk kiosk = new Kiosk();
        kiosk.setStore(store);
        kiosk.setNumber(request.getKioskNumber());

        // 새로운 사용자 생성
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setStore(store);
        newUser.setKiosk(kiosk);
        newUser.setRole(request.getRole()); // 0 또는 1 값(Constants 참조)
        newUser.setAdmin(savedAdmin);

        // 비밀번호 인코딩
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 인코딩 후 저장
        // Admin 과 User 의 비밀번호 인코딩 값 다르게 저장됨...

        // 데이터베이스에 저장
        storeRepository.save(store);
        kioskRepository.save(kiosk);
        userService.saveRegisterUser(newUser);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
