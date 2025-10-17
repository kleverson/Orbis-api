package com.orbis.controller;

import com.orbis.config.security.JwtService;
import com.orbis.domain.request.UserRegister;
import com.orbis.domain.response.GenericException;
import com.orbis.domain.response.GenericMessage;
import com.orbis.model.UserEntity;
import com.orbis.repository.UserRepository;
import com.orbis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name="User", description = "Endpoints for user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register new user",
            description = "Register a new user with username and password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = GenericMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos",
                            content = @Content)
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegister user) {
        UserEntity created = userService.registerNewUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(Map.of("id", created.getId(), "message", "User registered"));
    }
}
