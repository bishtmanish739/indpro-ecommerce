package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.dtos.LoginResponse;
import com.indpro.assignment.assignment.dtos.UserDTO;
import com.indpro.assignment.assignment.entity.Role;
import com.indpro.assignment.assignment.entity.User;
import com.indpro.assignment.assignment.repository.RoleRepository;
import com.indpro.assignment.assignment.repository.UserRepository;
import com.indpro.assignment.assignment.services.impl.UserDetailsServiceImpl;
import com.indpro.assignment.assignment.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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


        user.setRoles(assignDefaultRole());
        return userRepository.save(user);
    }
    private List<Role> assignDefaultRole(){
        List<Role> roles=new ArrayList<>();

        Optional<Role> exitingRole=roleRepository.findRoleByName("ROLE_USER");
        Role roleExist= exitingRole.orElse(
                null
        );
        if(roleExist != null){
            roles.add(roleExist);
            return  roles;
        }


        Role r=new Role();
        r.setName("ROLE_USER");
        roles.add(r);
        return  roles;
    }

    // Authenticate user and return JWT token
    public LoginResponse loginUser(UserDTO userDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

        if (!passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token= jwtUtil.generateToken(userDetails);
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);
        return  loginResponse;

    }
    public User getUserByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
}

