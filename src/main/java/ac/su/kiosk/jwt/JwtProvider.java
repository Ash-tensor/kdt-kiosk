package ac.su.kiosk.jwt;

import ac.su.kiosk.domain.User;
import ac.su.kiosk.repository.UserRepository;
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
public class JwtProvider {
    private final JwtProperty jwtProperty;
    private final UserRepository userRepository;


    // 토큰 생성
    public String makeToken(Date now, Date expiredAt, User user) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperty.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .setSubject(String.valueOf(user.getId()))
                .claim("role", user.getRole())      // 권한 정보
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecretKey())
                .compact();
    }

    // 토큰을 생성 후 전달
    public String generateToken(User user, Duration expiry) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + expiry.toMillis());
        return makeToken(now, expiredAt, user);
    }

    // 토큰 수신 후 검증
    public boolean validateToken(String token) {
        // 인코딩된 내용, 암호화된 signature 모두 의미 추출
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperty.getSecretKey())  // 암호키를 알고있는 서버에서
                    .parseClaimsJws(token);                     // 토큰을 해석
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        // 토큰 기반 클레임 데이터 해독
        return Jwts.parser()
                .setSigningKey(jwtProperty.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 수신 후 토큰 소유자 권한 조회
    public Authentication getAuthentication(String token) {
        // 토큰 정보를 통해 유저 인증 정보 확인
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if(claims.get("role") != null) {
            if(claims.get("role").equals("ADMIN")) {
                authorities = Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_ADMIN")    // ROLE_ 을 Spring Security 에서 자동으로 설정되기 떄문에 그 형식에 맞추어 지정
                );                                                       // hasAutority와 hasRole은 다르며 Role이 권한의 집합이다 hasRole 사용하면 접두사 ROLE_이 붙는다
            }
            else if(claims.get("role").equals("CUSTOMER")) {
                authorities = Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_CUSTOMER")
                );
            }
        }
        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                token,
                authorities
        );
    }

    // 토큰 수신 후 소유자 정보 조회
    public User getUser(String token) {
        Claims claims = getClaims(token);
        int userId = Integer.parseInt(claims.getSubject());
        return userRepository.findById(userId).get();
    }
}
