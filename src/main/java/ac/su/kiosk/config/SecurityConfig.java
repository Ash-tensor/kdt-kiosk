package ac.su.kiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity  // URL 요청에 대한 Spring Security 동작 활성화
public class SecurityConfig {
    @Bean
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
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
