package com.appbuilder.appbuilder.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    private final String token;
    private final UserProfileResponseDto userProfileDto;
}
