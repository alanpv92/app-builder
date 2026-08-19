package com.appbuilder.appbuilder.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email is not vaild")
    private final String email;

    @NotBlank(message = "password cannot be blank")
    private final String password;
}
