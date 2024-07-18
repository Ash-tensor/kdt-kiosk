package ac.su.kiosk.repository;

import ac.su.kiosk.domain.OptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionItemRepository extends JpaRepository<OptionItem, Long> {
}
