package com.appbuilder.appbuilder.dto.member;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InviteMemberRequestDto {

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email is not vaild")
    private final String email;

    @NotNull(message = "role cannot be empty")
    private final ProjectRole role;
}
