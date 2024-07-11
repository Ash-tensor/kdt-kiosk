package ac.su.kiosk.service;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Category;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.repository.AdminRepository;
import ac.su.kiosk.repository.CategoryRepository;
import ac.su.kiosk.repository.MenuRepository;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final MenuRepository menuRepository;
    private final StorageService storageService;
    private final CategoryRepository categoryRepository;

    // 스프링 시큐리티에서 사용하는 패스워드 암호화 객체
    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public Optional<Admin> findAdminByName(String name) {
        return adminRepository.findByName(name);
    }

    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

}
