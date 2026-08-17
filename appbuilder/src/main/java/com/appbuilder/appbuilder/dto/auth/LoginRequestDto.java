package com.appbuilder.appbuilder.dto.auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private final String email;
    private final String password;
}
