package ac.su.kiosk.service;

import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final MenuRepository menuRepository;

    public void addMenu() {
        // 메뉴 추가 로직

    }
}
