package com.appbuilder.appbuilder.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectCreationRequestDto {
    @NotBlank(message = "name cannot be blank")
    private final String name;
}
