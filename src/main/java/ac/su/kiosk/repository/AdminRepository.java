package ac.su.kiosk.repository;

import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    List<Admin> findByAdminID(Integer adminID);
}
