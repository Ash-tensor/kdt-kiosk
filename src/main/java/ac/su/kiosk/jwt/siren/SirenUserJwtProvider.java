package ac.su.kiosk.jwt.siren;


import ac.su.kiosk.domain.SirenUser;
import ac.su.kiosk.jwt.JwtProperty;
import ac.su.kiosk.repository.siren.SirenUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SirenUserJwtProvider {
    private final JwtProperty jwtProperty;
    private final SirenUserRepository sirenUserRepository;

    // 토큰 생성
    public String makeToken(Date now, Date expiredAt, SirenUser sirenUser) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperty.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .setSubject(String.valueOf(sirenUser.getId()))
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecretKey())
                .compact();
    }

    // 토큰 생성 후 전달
    public String generateToken(SirenUser sirenUser, Duration expiry) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + expiry.toMillis());
        return makeToken(now, expiredAt, sirenUser);
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperty.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperty.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰에서 인증 정보 추출
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (claims.get("role") != null) {
            if (claims.get("role").equals("USER")) {
                authorities = Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_USER")
                );
            }
            // 필요한 경우 추가적인 역할 설정 가능
        }
        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                token,
                authorities
        );
    }

    // 토큰에서 사용자 이름 추출
    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    // 토큰에서 SirenUser 정보 추출
    public SirenUser getSirenUser(String token) {
        Claims claims = getClaims(token);
        Long userId = Long.parseLong(claims.getSubject());
        return sirenUserRepository.findById(userId).orElse(null);
    }
}