package ac.su.kiosk.controller;

import ac.su.kiosk.dto.MenuDTO;
import ac.su.kiosk.dto.MenuPictureDTO;
import ac.su.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/menu")
public class MenuAddRestController {
    private final MenuService menuService;

    @PostMapping("/add_rest")
    public ResponseEntity<String> addMenu(@RequestParam("description") String description,
                                          @RequestParam("file") MultipartFile file,
                                          @RequestParam("name") String name,
                                          @RequestParam("category") int category,
                                          @RequestParam("price") int price) throws IOException {
        String message = menuService.addMenu(description, file, name, category, price);
        return ResponseEntity.ok("성공");
    }

    @PostMapping("/new_add_rest")
    public ResponseEntity<String> addMenu(@RequestPart MenuPictureDTO menuPictureDTO) {
        try {
            menuService.addMenu(menuPictureDTO);
        }
        catch (IOException e) {
            e.printStackTrace();
    }
        return ResponseEntity.ok("성공");

    }


    // 새로운 엔드포인트: 파일 업로드 없이 상품 추가
    @PostMapping("/add_rest_simple")
    public ResponseEntity<String> addMenuSimple(@RequestBody MenuDTO menuDto) {
        menuService.addMenuSimple(menuDto);
        return ResponseEntity.ok("성공");
    }
}
