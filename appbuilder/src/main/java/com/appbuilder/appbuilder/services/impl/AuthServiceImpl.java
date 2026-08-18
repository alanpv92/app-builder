package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.auth.AuthResponseDto;
import com.appbuilder.appbuilder.dto.auth.LoginRequestDto;
import com.appbuilder.appbuilder.dto.auth.SignupRequestDto;
import com.appbuilder.appbuilder.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponseDto signUp(SignupRequestDto signupRequestDto) {
        return null;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
