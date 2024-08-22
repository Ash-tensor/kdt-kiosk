package ac.su.kiosk.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperty jwtProperty;

}
