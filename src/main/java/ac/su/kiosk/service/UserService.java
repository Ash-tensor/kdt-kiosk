package ac.su.kiosk.service;

import ac.su.kiosk.constant.Role;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Customer;
import ac.su.kiosk.domain.User;
import ac.su.kiosk.jwt.AccessTokenDTO;
import ac.su.kiosk.jwt.JwtProvider;
import ac.su.kiosk.jwt.SpringUser;
import ac.su.kiosk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    public void saveAdminUser(Admin admin) {
        User user = new User();
        user.setAdmin(admin);
        user.setName(admin.getName());  // Admin은 name으로 로그인
        user.setPassword(admin.getPassword());
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public void saveCustomerUser(Customer customer) {
        User user = new User();
        user.setCustomer(customer);
        user.setName(customer.getPhoneNumber());    // Customer는 전화번호로 로그인
        user.setPassword(customer.getPassword());
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    // 로그인 전용 메서드 Override
    @Override
    public UserDetails loadUserByUsername(
            String name  // 로그인 ID 를 말함
    ) throws UsernameNotFoundException {
        Optional<User> registeredUser = userRepository.findByName(name);
        if (registeredUser.isEmpty()) {
            throw new UsernameNotFoundException(name);
        }
        return SpringUser.getSpringUserDetails(registeredUser.get());
    }

    public AccessTokenDTO getAccessToken(User user) {
        // Spring Security 로그인 전용 메서드 loadUserByUsername 사용해 인증
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(user.getName());
        } catch (Exception e) {
            return null;
        }
        // 비밀번호 체크
        if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            // AccessTokenDTO 로 Wrapping 및 리턴
            String accessToken = jwtProvider.generateToken(user, Duration.ofHours(1L));
            String tokenType = "Bearer";
            return new AccessTokenDTO(
                    accessToken, tokenType
            );
        }
        return null;
    }
}
