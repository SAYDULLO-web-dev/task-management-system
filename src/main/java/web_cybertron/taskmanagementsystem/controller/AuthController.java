package web_cybertron.taskmanagementsystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.LoginDTO;
import web_cybertron.taskmanagementsystem.payload.RegisterDto;
import web_cybertron.taskmanagementsystem.service.AuthService;
import web_cybertron.taskmanagementsystem.service.JWTService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JWTService jwtService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(),
                    loginDTO.getPassword()
            ));
            Users authenticatedPrincipal = (Users) authenticated.getPrincipal();
            String token = jwtService.generateToken(authenticatedPrincipal.getUsername());// getUsername() returns email
            return ResponseEntity.ok(token);
        }catch (Exception e) {
            return ResponseEntity.status(401).body(new ApiResponse("Email or password is incorrect", false));
        }
    }

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam String emailCode) {
        ApiResponse apiResponse=authService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
