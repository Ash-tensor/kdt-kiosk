package ac.su.kiosk.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// jwt 구조
// Authorization: Bearer Header.Payload.Signature
// Bearer : 소지자(여러 데이터를 토큰이 가지고 있는 타입)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // 아래 구조와 같은 토큰을 수신한 경우 효과적으로 Auth 수행
    // Authorization: Bearer Header.Payload.Signature
    private final JwtProvider jwtProvider;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private String getAccessToken(HttpServletRequest request) {
        // 토큰 전체 값 추출
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            // 접두사 제거한 토큰 값 리턴
            // = Claim 에 해당하는 String 을 리턴
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // 수신한 request 에서 토큰 추출
        String token = getAccessToken(request);
        if (token != null && jwtProvider.validateToken(token)) {
            // 토큰의 유효성 판단 후
            Authentication authentication = jwtProvider.getAuthentication(token);
            // 토큰 소유자 권한에 따라 요청 맥락에 권한 부여
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
