package com.example.BBookstore.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BBookstore.domain.User;
import com.example.BBookstore.domain.UserRepository;

import jakarta.validation.Valid;

@Controller
public class SignupController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
public String processSignup(@Valid SignupForm form, BindingResult result, Model model) {
    if (result.hasErrors()) {
        return "signup";
    }

    // Check if passwords match
    if (!form.getPassword().equals(form.getConfirmPassword())) {
        model.addAttribute("passwordError", "Passwords do not match!");
        return "signup";
    }

    // Check if username exists
    if (userRepository.findByUsername(form.getUsername()).isPresent()) {
        model.addAttribute("usernameError", "Username already exists!");
        return "signup";
    }

    // Save new user
    User newUser = new User();
    newUser.setUsername(form.getUsername());
    newUser.setPassword(passwordEncoder.encode(form.getPassword()));
    newUser.setEmail(form.getEmail()); // Set the email field
    newUser.setRole("USER"); // Default role

    userRepository.save(newUser);
    return "redirect:/login"; // Redirect to login after signup
}
}