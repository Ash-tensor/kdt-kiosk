package ac.su.kiosk.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="refreshToken")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @Column(nullable=false)
    private String refreshToken;
}
