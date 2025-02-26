package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.entity.enums.SystemRoleName;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.RegisterDto;
import web_cybertron.taskmanagementsystem.repository.UserRepo;

import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    //todo write code in method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

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
        sendConfirmationEmail(user.getEmailCode(), user.getEmail());
        return new ApiResponse("Users registered successfully", true);
    }

    public Boolean sendConfirmationEmail(String emailCode, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Confirmation code");
        mailMessage.setText("Your confirmation code is: " + emailCode);
        mailMessage.setFrom("no_reply_task-system-management.org");
        javaMailSender.send(mailMessage);
        return true;
    }
}
