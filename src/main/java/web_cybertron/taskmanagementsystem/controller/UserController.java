package web_cybertron.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Register user
    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return userService.register(user);
    }

    // Login user
    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        return userService.verify(user);
    }
}
