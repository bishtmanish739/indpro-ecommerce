package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.dtos.UserDTO;
import com.indpro.assignment.assignment.entity.User;
import com.indpro.assignment.assignment.repository.UserRepository;
import com.indpro.assignment.assignment.services.impl.UserDetailsServiceImpl;
import com.indpro.assignment.assignment.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Register a new user
    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    // Authenticate user and return JWT token
    public String loginUser(UserDTO userDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

        if (!passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(userDetails);
    }
}

