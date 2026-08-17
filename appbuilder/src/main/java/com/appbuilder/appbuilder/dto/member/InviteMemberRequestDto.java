package com.appbuilder.appbuilder.dto.member;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import lombok.Data;

@Data
public class InviteMemberRequestDto {

    private final String email;
    private final ProjectRole role;
}
