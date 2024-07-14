// MenuService 통합.


//package ac.su.kiosk.service;
//
//import ac.su.kiosk.domain.Menu;
//import ac.su.kiosk.repository.MenuRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class MenuUpdateService {
//    private final MenuRepository menuRepository;
//
////    public List<Menu> getAll() {
////        return menuRepository.findAll();
////    }
////
////    public List<Menu> getMenusByCategory(Long categoryId) {
////        return menuRepository.findByCategoryId(categoryId);
////    }
////
////    public Menu addMenu(Menu menu) {
////        return menuRepository.save(menu);
////    }
//
//    public Menu updateMenu(int id, Menu menu) {
//        Menu existingMenu = menuRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
//        existingMenu.setName(menu.getName());
//        existingMenu.setCategory(menu.getCategory());
//        existingMenu.setPrice(menu.getPrice());
//        existingMenu.setSoldOut(menu.isSoldOut());
//        existingMenu.setTag(menu.getTag());
//        return menuRepository.save(existingMenu);
//    }
//
////    public void deleteMenu(int id) {
////        menuRepository.deleteById(id);
////    }
//}
