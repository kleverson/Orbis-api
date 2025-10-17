package com.orbis.service;

import com.orbis.domain.request.UserRegister;
import com.orbis.model.UserEntity;
import com.orbis.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity registerNewUser(UserRegister user){

        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        var newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("USER");
        userRepository.save(newUser);

        return newUser;
    }

}
