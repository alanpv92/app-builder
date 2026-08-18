package com.appbuilder.appbuilder.utils.mappers;
import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectCreationRequestDto;
import com.appbuilder.appbuilder.dto.project.ProjectResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectSummaryResponseDto;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import com.appbuilder.appbuilder.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectEntity fromProjectCreationRequestDto(ProjectCreationRequestDto projectCreationRequestDto, UserEntity owner) {
        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectCreationRequestDto.getName());
        projectEntity.setOwner(owner);
        projectEntity.setIsPublic(false);
       return projectEntity;
    }

    public ProjectResponseDto fromProjectEntity(ProjectEntity projectEntity, UserProfileResponseDto owner) {
       return ProjectResponseDto.builder()
               .id(projectEntity.getId())
               .name(projectEntity.getName())
               .updatedAt(projectEntity.getUpdatedAt())
               .createdAt(projectEntity.getCreatedAt())
               .owner(owner)
               .build();
    }

    public ProjectSummaryResponseDto fromProjectEntity(ProjectEntity projectEntity) {
        return ProjectSummaryResponseDto.builder()
                .id(projectEntity.getId())
                .name(projectEntity.getName())
                .createdAt(projectEntity.getCreatedAt())
                .updatedAt(projectEntity.getUpdatedAt())
                .build();
    }
}
