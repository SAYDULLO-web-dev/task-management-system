package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.User;
import web_cybertron.taskmanagementsystem.entity.enums.SystemRoleName;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.RegisterDto;
import web_cybertron.taskmanagementsystem.repository.UserRepository;

import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //todo write code in method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ApiResponse("This user is already registered by entered email.", false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_ROLE_USER
        );
        int confirmationCode = new Random().nextInt(6);
        user.setEmailCode(String.valueOf(confirmationCode));
        userRepository.save(user);
        new EmailService().sendConfirmationEmail(user.getEmailCode(), user.getEmail());
        return new ApiResponse("User registered successfully", true);
    }
}
