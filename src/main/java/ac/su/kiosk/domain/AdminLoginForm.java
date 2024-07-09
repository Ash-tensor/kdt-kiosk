package ac.su.kiosk.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminLoginForm {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String adminEmail;

    @NotBlank(message = "Password is mandatory")
    private String password;

    // Getters and Setters
    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
