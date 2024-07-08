package ac.su.kiosk.service;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin create(String adminName, String password) {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(password));
        this.adminRepository.save(admin);
        return admin;
    }
}
