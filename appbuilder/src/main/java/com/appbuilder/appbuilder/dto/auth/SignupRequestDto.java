package com.appbuilder.appbuilder.dto.auth;

import lombok.Data;

@Data
public class SignupRequestDto {
    private final String name;
    private final String email;
    private final String password;
}
