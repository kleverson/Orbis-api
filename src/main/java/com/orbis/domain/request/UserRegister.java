package com.orbis.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

    @NotBlank(message="User name is mandatory")
    @Email(message = "Email should be valid")
    public String username;

    @NotBlank(message="Name is mandatory")
    public String name;

    @NotBlank(message="Password is mandatory")
    @Size(min=6, message="Password must be at least 6 characters long")
    public String password;
}
