package com.appbuilder.appbuilder.dto.member;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMemberRoleRequestDto {
    @NotNull(message = "role cannot be empty")
    private final ProjectRole role;
}
