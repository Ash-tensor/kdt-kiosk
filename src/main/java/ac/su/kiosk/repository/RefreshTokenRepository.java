package ac.su.kiosk.repository;

import ac.su.kiosk.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshToken(String token);
}
