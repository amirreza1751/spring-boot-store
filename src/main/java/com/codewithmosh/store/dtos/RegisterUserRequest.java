package com.codewithmosh.store.dtos;

import com.codewithmosh.store.Validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "name must not be blank")
    @Size(min = 4, max = 255, message = "Name must be between 5 and 255 characters.")
    private String name;

    @NotBlank
    @Email
    @Lowercase(message = "Email must be lowercase.")
    private String email;

    @NotBlank
    private String password;
}
