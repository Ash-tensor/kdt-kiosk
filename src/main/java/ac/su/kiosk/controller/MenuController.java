package ac.su.kiosk.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/{category}/{item}")
    public ResponseEntity<String> getMenuItem(@PathVariable String category, @PathVariable String item) {
        try {
            ClassPathResource resource = new ClassPathResource("static/menu/" + category + "/" + item + ".json");
            String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return new ResponseEntity<>(jsonContent, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
