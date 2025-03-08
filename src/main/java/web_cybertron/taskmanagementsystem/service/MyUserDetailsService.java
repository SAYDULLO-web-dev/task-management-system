package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.entity.principals.UserPrincipal;
import web_cybertron.taskmanagementsystem.repository.UserRepo;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Users> user = userRepo.findByEmail(email);

        if (user.isPresent()) {
            return new UserPrincipal(user.orElse(null));
        }
        System.out.println("User Not Found");
        throw new UsernameNotFoundException("User Not Found");

    }
}
