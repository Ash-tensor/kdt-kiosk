package ac.su.kiosk.service;

import ac.su.kiosk.repository.StoreGuardImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreGuardImgService {
    private final StoreGuardImgRepository storeGuardImgRepository;
}
