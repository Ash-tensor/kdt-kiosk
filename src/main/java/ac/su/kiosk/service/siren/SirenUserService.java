package ac.su.kiosk.service.siren;

import ac.su.kiosk.domain.SirenUser;
import ac.su.kiosk.domain.RefreshToken;
import ac.su.kiosk.dto.siren.SirenUserRegisterDTO;
import ac.su.kiosk.jwt.siren.SirenUserJwtProvider;
import ac.su.kiosk.repository.siren.SirenUserRepository;
import ac.su.kiosk.repository.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SirenUserService {
    private final SirenUserRepository sirenUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final SirenUserJwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입 메서드
    public void registerUser(SirenUserRegisterDTO sirenUserRegisterDTO) {
        // DTO에서 엔티티로 변환
        SirenUser sirenUser = new SirenUser();
        sirenUser.setName(sirenUserRegisterDTO.getName());
        sirenUser.setPassword(passwordEncoder.encode(sirenUserRegisterDTO.getPassword()));
        sirenUser.setPhoneNumber(sirenUserRegisterDTO.getPhoneNumber());
        sirenUser.setCreatedAt(LocalDate.now());
        sirenUser.setUpdatedAt(LocalDate.now());

        // 사용자 저장
        sirenUserRepository.save(sirenUser);
    }

    // 로그인 인증 메서드
    public boolean authenticateUser(SirenUserRegisterDTO sirenUserRegisterDTO) {
        // 사용자 이름으로 사용자 조회
        Optional<SirenUser> userOptional = sirenUserRepository.findByName(sirenUserRegisterDTO.getName());
        if (userOptional.isPresent()) {
            SirenUser user = userOptional.get();
            // 비밀번호 검증
            return passwordEncoder.matches(sirenUserRegisterDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    // 사용자 정보 조회 메서드
    public SirenUser getUserById(Long id) {
        return sirenUserRepository.findById(id).orElse(null);
    }

    // JWT 토큰 생성
    public String generateAccessToken(SirenUser user) {
        return jwtProvider.generateToken(user, Duration.ofHours(1L));
    }

    // RefreshToken 저장
    public void saveRefreshToken(String token) {
        refreshTokenRepository.save(new RefreshToken(token));
    }

    public Optional<SirenUser> getUserByName(String name) {
        return sirenUserRepository.findByName(name);
    }

    // 리프레시 토큰 검증
    public boolean validateRefreshToken(String token) {
        return refreshTokenRepository.existsByRefreshToken(token);
    }


    public void updateSirenUserLocation(Long id, String address, Double latitude, Double longitude) {
        SirenUser sirenUser = sirenUserRepository.findById(id).orElse(null);
        if (sirenUser != null) {
            sirenUser.setAddress(address);
            sirenUser.setLatitude(latitude);
            sirenUser.setLongitude(longitude);

            sirenUserRepository.save(sirenUser);
        } else {
            throw new EntityNotFoundException("SirenUser with id " + id + " not found");
        }
    }
}
