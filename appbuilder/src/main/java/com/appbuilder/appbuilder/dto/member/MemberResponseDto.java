package com.appbuilder.appbuilder.dto.member;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto {

    @JsonProperty("user_id")
    private final String userId;
    private final String email;
    private final String name;
    @JsonProperty("avatar_url")
    private final String avatarUrl;
    private final ProjectRole role;
    private final LocalDateTime invitedAt;

}
