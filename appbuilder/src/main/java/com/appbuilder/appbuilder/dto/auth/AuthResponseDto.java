package com.appbuilder.appbuilder.dto.auth;

import lombok.Data;

@Data
public class AuthResponseDto {
    private final String token;
    private final UserProfileResponseDto userProfileDto;
}
