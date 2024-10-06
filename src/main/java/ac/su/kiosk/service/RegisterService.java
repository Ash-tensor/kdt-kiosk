//package ac.su.kiosk.service;
//
//import ac.su.kiosk.constant.Role;
//import ac.su.kiosk.domain.Admin;
//import ac.su.kiosk.domain.Register;
//import ac.su.kiosk.domain.User;
//import ac.su.kiosk.repository.RegisterRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//public class RegisterService {
//    private final RegisterRepository registerRepository;
//    public final PasswordEncoder passwordEncoder;
//
//    public Optional<Register> findRegisterByName(String name) {
//        return registerRepository.findByName(name);
//    }
//
//    public Register saveRegister(Register register) {
//        register.setPassword(passwordEncoder.encode(register.getPassword()));
//        return registerRepository.save(register);
//    }
//
//    public Optional<Register> findRegisterByEmail(String email) {
//        return registerRepository.findByRegisterEmail(email);
//    }
//
//    public void saveRegister(Register register) {
//        User user = new User();
//        user.setAdmin(register);
//        user.setName(register.getName());  // Admin은 name으로 로그인
//        user.setPassword(register.getPassword());
//        user.setRole(Role.ADMIN);
//        registerRepository.save(user);
//    }
//}
