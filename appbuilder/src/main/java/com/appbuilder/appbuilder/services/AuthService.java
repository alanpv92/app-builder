package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.auth.AuthResponseDto;
import com.appbuilder.appbuilder.dto.auth.LoginRequestDto;
import com.appbuilder.appbuilder.dto.auth.SignupRequestDto;

public interface AuthService {

    AuthResponseDto signUp(SignupRequestDto signupRequestDto);
    AuthResponseDto login(LoginRequestDto loginRequestDto);
}
