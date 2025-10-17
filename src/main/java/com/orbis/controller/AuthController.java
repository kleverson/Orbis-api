package com.orbis.controller;

import com.orbis.config.security.JwtService;
import com.orbis.domain.request.UserLogin;
import com.orbis.domain.response.TokenUser;
import com.orbis.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin){
        var user = userRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid or/and password "));

        if(passwordEncoder.matches(userLogin.getPassword(), user.getPassword())){
            var accessToken =  jwtService.generateToken(
                    user.getUsername()
            );

            var token = new TokenUser(
                    accessToken,
                    "Bearer"
            );

            return ResponseEntity.ok(token);
        }
        else {
            throw new RuntimeException("Invalid username or password");
        }

    }

}
