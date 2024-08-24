package ac.su.kiosk.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SpringUser extends User {  // Wrapper 로 작용
    public SpringUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }

    public SpringUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public static UserDetails getSpringUserDetails(
            ac.su.kiosk.domain.User appUser)
    {
        return User.builder()
                .username(appUser.getName())
                .password(appUser.getPassword())
                .roles(appUser.getRole().toString())
                .build();
    }
}
