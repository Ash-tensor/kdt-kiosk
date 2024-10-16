package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Store;
import ac.su.kiosk.domain.StoreGuardImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreGuardImgRepository extends JpaRepository<StoreGuardImg, Integer> {
    Optional<List<StoreGuardImg>> findByStore(Store store);
    Optional<StoreGuardImg> findByStoreAndImgUrl(Store store, String imgUrl);
    Optional<StoreGuardImg> findByImgUrl(String imgUrl);
}
