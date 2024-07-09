package ac.su.kiosk.service;

import ac.su.kiosk.config.JwtUtil;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.repository.AdminRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
    }

    public boolean authenticate(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return passwordEncoder.matches(password, admin.getPassword());
    }

    public String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }

    public void create(String Name, String Email, String password) {
        Admin admin = new Admin();
        admin.setName(Name);
        admin.setEmail(Email);
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Admin admin = adminRepository.findByUsername(username)
//        Admin admin = adminRepository.findByName(username)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//        return org.springframework.security.core.userdetails.User.builder()
//            .username(admin.getName())
//            .password(admin.getPassword())
//            .build();
//    }
}
