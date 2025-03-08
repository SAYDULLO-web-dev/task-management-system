package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.entity.enums.SystemRoleName;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.RegisterDto;
import web_cybertron.taskmanagementsystem.repository.UserRepo;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

//    //todo write code in method
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return new ApiResponse("This user is already registered by entered email.", false);
        }
        Users user = new Users(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_ROLE_USER
        );
        int confirmationCode = new Random().nextInt(9999);
        user.setEmailCode(String.valueOf(confirmationCode).substring(0, 4));
        userRepo.save(user);
        emailService.sendConfirmationEmail(user.getEmailCode(), user.getEmail());
        return new ApiResponse("Users registered successfully", true);
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<Users> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            Users users = user.get();
            if (users.getEmailCode().equals(emailCode)) {
                users.setEnabled(true);
                users.setEmailCode(null);
                userRepo.save(users);
                return new ApiResponse("Email verified successfully", true);
            }
            return new ApiResponse("Email code is incorrect", false);
        }

        return null;

    }
}
