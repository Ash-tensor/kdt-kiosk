package ac.su.kiosk.service;

//import javax.servlet.http.HttpServletRequest;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final AdminRepository adminRepository;

    public Admin autoRegister() {
        // 관리자 자동 생성
//        adminRepository.findById()
//        request.getSession().getId; << 이게 맞는데 일단 무시하고
//        테스트 코드를 짜도록 하겠습니다

        Admin admin = Admin.builder()
                .name("admin이름")
                .email("example.example.com")
                .address("서울특별시 노원구 화랑로")
                        .build();
        return adminRepository.save(admin);
    }
}
