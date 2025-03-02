package com.example.BBookstore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.BBookstore.domain.User;
import com.example.BBookstore.domain.UserRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Add users if the repository is empty
        if (userRepository.count() == 0) {
            // Create a regular user
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user124"));
            user.setEmail("user@example.com");
            user.setRole("USER");
            userRepository.save(user);
            
            // Create an admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin124"));
            admin.setEmail("admin@example.com");
            admin.setRole("ADMIN");
            userRepository.save(admin);
            
            System.out.println("Database initialized with users");
        }
    }
}