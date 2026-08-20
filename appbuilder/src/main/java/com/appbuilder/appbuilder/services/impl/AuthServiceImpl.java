package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.auth.AuthResponseDto;
import com.appbuilder.appbuilder.dto.auth.LoginRequestDto;
import com.appbuilder.appbuilder.dto.auth.SignupRequestDto;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.AuthService;
import com.appbuilder.appbuilder.services.JwtService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.appbuilder.appbuilder.utils.mappers.AuthMapper;
import com.appbuilder.appbuilder.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto signUp(SignupRequestDto signupRequestDto) {

        if(userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new BadRequestException(ErrorMessageConstants.EMAIL_ALREADY_EXISTS);
        }
        final UserEntity userToBeCreated=userMapper.fromSignUpDto(signupRequestDto);
        userToBeCreated.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        final UserEntity createdUser=userRepository.save(userToBeCreated);
        return authMapper.fromUserEntity(jwtService.generateToken(createdUser), createdUser);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

        final UserEntity existingUser=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new BadRequestException(ErrorMessageConstants.EMAIL_NOT_FOUND)
        );

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), existingUser.getPassword())) {
            throw new BadRequestException(ErrorMessageConstants.PASSWORD_DOES_NOT_MATCH);
        }

        return authMapper.fromUserEntity(jwtService.generateToken(existingUser), existingUser);
    }
}
