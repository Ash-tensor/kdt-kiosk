package ac.su.kiosk.controller.siren;

import ac.su.kiosk.domain.SirenUser;
import ac.su.kiosk.dto.siren.SirenUserRegisterDTO;
import ac.su.kiosk.jwt.AccessTokenDTO;
import ac.su.kiosk.jwt.siren.SirenUserJwtProvider;
import ac.su.kiosk.service.siren.SirenUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kk/siren/user")
public class SirenUserController {
    private final SirenUserService sirenUserService;
    private final SirenUserJwtProvider jwtProvider;

    // 회원가입 엔드포인트
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SirenUserRegisterDTO sirenUserRegisterDTO) {
        sirenUserService.registerUser(sirenUserRegisterDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<AccessTokenDTO> loginUser(@RequestBody SirenUserRegisterDTO sirenUserRegisterDTO) {
        boolean isAuthenticated = sirenUserService.authenticateUser(sirenUserRegisterDTO);
        if (isAuthenticated) {
            Optional<SirenUser> optUser = sirenUserService.getUserByName(sirenUserRegisterDTO.getName());
            if(!optUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            SirenUser user = optUser.get();
            if (user != null) {
                // 액세스 토큰 생성
                String accessToken = jwtProvider.generateToken(user, Duration.ofHours(1L));
                String tokenType = "Bearer";

                // 리프레시 토큰 생성
                String refreshToken = jwtProvider.generateToken(user, Duration.ofHours(3L));
                sirenUserService.saveRefreshToken(refreshToken);

                // 로그인 응답
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO(accessToken, tokenType);
                return ResponseEntity.ok(accessTokenDTO);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    // 리프레시 토큰을 사용하여 새로운 액세스 토큰 발급
    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenDTO> refreshAccessToken(@RequestBody String refreshToken) {
        if (sirenUserService.validateRefreshToken(refreshToken)) {
            SirenUser user = jwtProvider.getSirenUser(refreshToken);
            if (user != null) {
                // 새 액세스 토큰 생성
                String accessToken = jwtProvider.generateToken(user, Duration.ofHours(1L));
                String tokenType = "Bearer";

                // 새로운 액세스 토큰 응답
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO(accessToken, tokenType);
                return ResponseEntity.ok(accessTokenDTO);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    // 사용자 정보 조회 엔드포인트
    @GetMapping("/{id}")
    public ResponseEntity<SirenUser> getUserById(@PathVariable Long id) {
        SirenUser user = sirenUserService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
