package com.appbuilder.appbuilder.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileResponseDto {
    private final String id;
    private final String email;
    private final String name;
    @JsonProperty("avatar_url")
    private final String avatarUrl;
}
