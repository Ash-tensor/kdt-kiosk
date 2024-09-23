package ac.su.kiosk.service.siren;

import ac.su.kiosk.domain.SirenUser;
import ac.su.kiosk.repository.siren.SirenUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SirenUserDetailService {
    private final SirenUserRepository sirenUserRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SirenUser> optionalSirenUser = sirenUserRepository.findByName(username);

        if (!optionalSirenUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        SirenUser sirenUser = optionalSirenUser.get();
        return new User(
                sirenUser.getName(),
                sirenUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
