package com.appbuilder.appbuilder.dto.project;

import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponseDto {
    private final String id;
    private final String name;
    @JsonProperty("created_at")
    private final LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private final LocalDateTime updatedAt;
    private final UserProfileResponseDto owner;

}
