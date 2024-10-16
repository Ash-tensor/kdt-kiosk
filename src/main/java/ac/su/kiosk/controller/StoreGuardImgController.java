package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Store;
import ac.su.kiosk.domain.StoreGuardImg;
import ac.su.kiosk.domain.TestEntity;
import ac.su.kiosk.domain.User;
import ac.su.kiosk.dto.GuardImgNameDTO;
import ac.su.kiosk.jwt.JwtProvider;
import ac.su.kiosk.repository.StoreGuardImgRepository;
import ac.su.kiosk.repository.UserRepository;
import ac.su.kiosk.service.StorageService;
import ac.su.kiosk.service.StoreGuardImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/img")
public class StoreGuardImgController {
    private final StoreGuardImgService storeGuardImgService;
    private final StoreGuardImgRepository storeGuardImgRepository;
    private final StorageService storageService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @GetMapping("/guardImgs")   // 이미지가 다양할 경우가 있기 때문
    public List<String> sendGuardImgs(@RequestHeader("Authorization") String token){
        List<String> imgUrls = new ArrayList<>();
        String accessTokenDTO;
        Store store;
        if (token != null && token.startsWith("Bearer")) {
            accessTokenDTO = token.substring(7);
            store = jwtProvider.getStore(accessTokenDTO);
            if (store == null) {
                return null;
            }
            if(storeGuardImgRepository.findByStore(store).isPresent()){
                List<StoreGuardImg> guardImgs = storeGuardImgRepository.findByStore(store).get();
                for (StoreGuardImg guardImg : guardImgs) {
                    imgUrls.add(guardImg.getImgUrl());
                }
                return imgUrls;
            }
        }
        return null;
    }

    @GetMapping("/guardImgsName")   // 이미지가 다양할 경우가 있기 때문
    public List<GuardImgNameDTO> sendGuardImgsName(@RequestHeader("Authorization") String token){
        List<GuardImgNameDTO> imgUrls = new ArrayList<>();
        String accessTokenDTO;
        Store store;
        if (token != null && token.startsWith("Bearer")) {
            accessTokenDTO = token.substring(7);
            store = jwtProvider.getStore(accessTokenDTO);
            if (store == null) {
                return null;
            }
            if(storeGuardImgRepository.findByStore(store).isPresent()){
                List<StoreGuardImg> guardImgs = storeGuardImgRepository.findByStore(store).get();
                for (StoreGuardImg guardImg : guardImgs) {
                    GuardImgNameDTO guardImgNameDTO = new GuardImgNameDTO();
                    guardImgNameDTO.setUrl(guardImg.getImgUrl());
                    guardImgNameDTO.setName(guardImg.getImgName());
                    imgUrls.add(guardImgNameDTO);
                }
                return imgUrls;
            }
        }
        return null;
    }

    @PostMapping("/saveGuardImg")
    public ResponseEntity<String> saveGuardImg(@RequestPart("file") MultipartFile file, @RequestPart("fileName") String fileName, @RequestHeader("Authorization") String token) throws IOException {
        try {
            String message = storageService.uploadFile(file);
            StoreGuardImg storeGuardImg = new StoreGuardImg();
            String accessTokenDTO;
            Store store;
            if (token != null && token.startsWith("Bearer")) {
                accessTokenDTO = token.substring(7);
                store = jwtProvider.getStore(accessTokenDTO);
                if(userRepository.findByStore(store).isEmpty()){
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                storeGuardImg.setStore(store);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            storeGuardImg.setImgUrl(message);
            storeGuardImg.setImgName(fileName);
            storeGuardImgRepository.save(storeGuardImg);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteGuardImg(@RequestParam String url) {
        storeGuardImgRepository.delete(storeGuardImgRepository.findByImgUrl(url).get());
        return ResponseEntity.ok("삭제됨");

    }
}
