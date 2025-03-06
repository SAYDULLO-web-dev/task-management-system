package web_cybertron.taskmanagementsystem.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Username is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Password is required")
    private String password;
}
