package ac.su.kiosk.service;

import ac.su.kiosk.domain.Kiosk;
import ac.su.kiosk.domain.Store;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KioskService {
    public Kiosk makeKiosk(Store store) {
        Kiosk kiosk = new Kiosk();
        kiosk.setStore(store);
        String kioskNumber = UUID.randomUUID().toString();
        kiosk.setNumber(kioskNumber); // 이거 일련번호인듯 아마
        return kiosk;
    }
}
