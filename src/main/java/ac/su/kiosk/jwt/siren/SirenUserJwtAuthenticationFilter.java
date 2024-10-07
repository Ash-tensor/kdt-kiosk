package ac.su.kiosk.jwt.siren;

    import ac.su.kiosk.service.siren.SirenUserDetailService;
    import ac.su.kiosk.service.siren.SirenUserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import  jakarta.servlet.FilterChain;
    import  jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import  jakarta.servlet.http.HttpServletResponse;
    import java.io.IOException;

    @RequiredArgsConstructor
    @Component
    public class SirenUserJwtAuthenticationFilter extends OncePerRequestFilter {

        private final SirenUserJwtProvider jwtProvider;
        private final SirenUserDetailService sirenUserDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {

            String path = request.getRequestURI();
            // SirenUser 관련 경로만 JWT 필터를 적용
            if (path.startsWith("/api/kk/siren/user/")) {
                String header = request.getHeader("Authorization");
                String token = null;
                String username = null;

                if (header != null && header.startsWith("Bearer ")) {
                    token = header.substring(7); // "Bearer " 이후의 토큰 추출
                    username = jwtProvider.getUsernameFromToken(token); // 토큰으로부터 사용자 이름 추출
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = sirenUserDetailsService.loadUserByUsername(username); // 사용자 정보를 로드

                    if (jwtProvider.validateToken(token)) {
                        // JWT 토큰이 유효하면 인증 정보를 설정
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }

            chain.doFilter(request, response); // 필터 체인에서 다음 필터로 요청 전달
        }

    }
