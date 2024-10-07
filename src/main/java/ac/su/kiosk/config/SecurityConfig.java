package ac.su.kiosk.config;

import ac.su.kiosk.jwt.JwtAuthenticationFilter;
import ac.su.kiosk.jwt.JwtProvider;
import ac.su.kiosk.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  // URL 요청에 대한 Spring Security 동작 활성화
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    // 기존 SecurityFilter
/*    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        new AntPathRequestMatcher("/**"), // 로그인 경로 허용
                                        new AntPathRequestMatcher("/error")
                                ).permitAll() // 위의 경로들은 모두 인증 없이 접근 가능
                                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                );
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login") // 사용자 정의 로그인 페이지
//                                .defaultSuccessUrl("/home") // 로그인 성공 시 리디렉션할 URL
//                )
//                .logout(logout ->
//                        logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 사용자 정의 로그아웃 URL
//                                .logoutSuccessUrl("/login") // 로그아웃 성공 시 리디렉션할 URL
//                                .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
//                );
        return http.build();
    }*/

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) // Deprecated됨
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                // JWT는 세션 사용 안함
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // React는 Form이 아니기 때문에 사용 안함
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(  // 요청 인가 여부 결정을 위한 조건 판단
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/**").permitAll() // 모든 요청이 허용됨
                                .requestMatchers("/api/kk/kiosk/**").permitAll()
                                .requestMatchers("/api/kk/siren/user/**").permitAll()
//                                .requestMatchers("/admin/category/**").authenticated()
//                                .requestMatchers("/admin/menu/**").authenticated()
//                                .requestMatchers("/admin/payment/**").authenticated()
//                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, refreshTokenRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtProvider, refreshTokenRepository);
    }
}
