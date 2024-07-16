package ac.su.kiosk.service;

import ac.su.kiosk.domain.OptionItem;
import ac.su.kiosk.repository.OptionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionItemService {

    private final OptionItemRepository optionItemRepository;

    public OptionItem getOptionItemById(Long id) {
        Optional<OptionItem> optionItem = optionItemRepository.findById(id);
        return optionItem.orElse(null);
    }

    public List<OptionItem> getAllOptionItems() {
        return optionItemRepository.findAll();
    }

    public OptionItem createOptionItem(OptionItem optionItem) {
        return optionItemRepository.save(optionItem);
    }

    public OptionItem updateOptionItem(Long id, OptionItem optionItem) {
        if (optionItemRepository.existsById(id)) {
            optionItem.setId(id);
            return optionItemRepository.save(optionItem);
        } else {
            return null;
        }
    }

    public void deleteOptionItem(Long id) {
        optionItemRepository.deleteById(id);
    }
}
