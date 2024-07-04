//package ac.su.kiosk.controller;
//
//import ac.su.kiosk.service.MenuService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@RequiredArgsConstructor
//@RequestMapping("/admin/menu")
//@Controller
//public class MenuAddController {
//    private final MenuService menuService;
//
//    @GetMapping("/add")
//    public String addMenu() {
//        return "admin/menu/add";
//    }
//
//    @PostMapping("/add")
//    public String addMenu(String menuDescription, String menuName, int categoryID, int basePrice) {
//        menuService.addMenu(menuDescription, menuName, categoryID, basePrice);
//        return "redirect:/admin/menu/add";
//    }
//
//
//}
