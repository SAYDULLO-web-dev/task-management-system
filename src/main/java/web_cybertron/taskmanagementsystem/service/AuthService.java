package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.entity.enums.SystemRoleName;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.RegisterDto;
import web_cybertron.taskmanagementsystem.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ApiResponse("This user is already registered by entered email.", false);
        }
        Users user = new Users(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_ROLE_USER
        );
        int confirmationCode = 1000 + new Random().nextInt(9000);
        user.setEmailCode(String.valueOf(confirmationCode));
        userRepository.save(user);
        try {
            emailService.sendConfirmationEmail(user.getEmailCode(), user.getEmail());
        } catch (Exception e) {
            System.out.println("Error occurred at sending email: " + e.getMessage());
        }
        return new ApiResponse("Users registered successfully", true);
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Users users = user.get();
            if (users.getEmailCode().equals(emailCode)) {
                users.setEnabled(true);
                users.setEmailCode(null);
                userRepository.save(users);
                return new ApiResponse("Account verified successfully", true);
            }
            return new ApiResponse("Verify code is incorrect", false);
        }
        return new ApiResponse("No such email (or account) found", false);
    }
}
