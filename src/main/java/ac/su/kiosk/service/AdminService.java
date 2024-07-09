package ac.su.kiosk.service;

import ac.su.kiosk.config.JwtUtil;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    public void create(String Name, String Email, String password) {
        if (adminRepository.existsByEmail(Email)) {
            throw new IllegalStateException("이미 등록된 관리자 입니다.");
        }

        Admin admin = new Admin();
        admin.setName(Name);
        admin.setEmail(Email);
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);
    }

    public boolean authenticate(String Email, String password) {
        Admin admin = adminRepository.findByEmail(Email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        return passwordEncoder.matches(password, admin.getPassword());
    }

    public String generateToken(String Email) {
        return jwtUtil.generateToken(Email);
    }

//    public Admin create(String adminName, String email, String password) {
//        Admin admin = new Admin();
//        admin.setName(adminName);
//        admin.setEmail(email);
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        admin.setPassword(passwordEncoder.encode(password));
//        this.adminRepository.save(admin);
//        return admin;
//    }
}
