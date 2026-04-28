package com.healthcare.recomendation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthcare.recomendation.dto.LoginRequest;
import com.healthcare.recomendation.dto.LoginResponse;
import com.healthcare.recomendation.models.Users;
import com.healthcare.recomendation.repository.UserRepository;

@Service
public class AuthService {
	
	
	@Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public LoginResponse authenticateUser(LoginRequest request) {
        Optional<Users> userOptional = userRepository.findByEmailId(request.getEmailId());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            // Verify password
            if (request.getPassword().equals(user.getPassword())) {
                return new LoginResponse(user.getEmailId(), user.getRoles(), "Login successful");
            } else {
                return new LoginResponse(request.getEmailId(), "", "Invalid password");
            }
        } else {
            return new LoginResponse(request.getEmailId(), "", "User not found");
        }
    }

}
