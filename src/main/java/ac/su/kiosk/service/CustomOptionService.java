package ac.su.kiosk.service;

import ac.su.kiosk.domain.CustomOption;
import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.dto.CustomOptionDTO;
import ac.su.kiosk.repository.CustomOptionRepository;
import ac.su.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOptionService {
    private final CustomOptionRepository customOptionRepository;
    private final MenuRepository menuRepository;

    public List<CustomOption> getCustomOptionsByMenu(int id) {
        return customOptionRepository.findCustomOptionsByMenuId(id);
    }

    public List<CustomOption> getAllCustomOptions() {
        return customOptionRepository.findAll();
    }

    public List<CustomOptionDTO> getAllCustomOptionsWithMenuName() {
        return customOptionRepository.findAllCustomOptionsWithMenuName();
    }

    public CustomOption addCustomOption(String name, Double additionalPrice, Integer menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("Invalid menu ID"));
        CustomOption customOption = new CustomOption();
        customOption.setName(name);
        customOption.setAdditionalPrice(additionalPrice);
        customOption.setMenu(menu);
        return customOptionRepository.save(customOption);
    }

    public void deleteCustomOption(Long id) {
        customOptionRepository.deleteById(id);
    }
}
