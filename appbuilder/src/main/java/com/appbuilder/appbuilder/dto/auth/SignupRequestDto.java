package com.appbuilder.appbuilder.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequestDto {
    private final String name;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email is not vaild")
    private final String email;

    @NotBlank(message = "password cannot be empty")
    private final String password;
}
