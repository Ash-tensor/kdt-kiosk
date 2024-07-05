package ac.su.kiosk.service;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.repository.CustomOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomOptionService {
    @Autowired
    private CustomOptionRepository customOptionRepository;

    public List<CustomOption> getCustomOptionsByMenu(Long id) {
        return customOptionRepository.findCustomOptionsByMenuId(id);
    }
}
