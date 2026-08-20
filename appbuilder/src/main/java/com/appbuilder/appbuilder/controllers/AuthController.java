package com.appbuilder.appbuilder.controllers;

import com.appbuilder.appbuilder.dto.auth.AuthResponseDto;
import com.appbuilder.appbuilder.dto.auth.LoginRequestDto;
import com.appbuilder.appbuilder.dto.auth.SignupRequestDto;
import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.appbuilder.appbuilder.services.AuthService;
import com.appbuilder.appbuilder.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signUp(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody  LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @GetMapping(path = "/me")
    public ResponseEntity<UserProfileResponseDto> getProfile(){
        var dummyUserId="123";
        return ResponseEntity.ok(userService.getProfile(dummyUserId));
    }

}
