package com.appbuilder.appbuilder.utils.mappers;

import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberMapper {

    public MemberResponseDto fromProjectMemberEntity(ProjectMemberEntity projectMemberEntity) {
        return MemberResponseDto.builder()
                .email(projectMemberEntity.getUserEntity().getEmail())
                .name(projectMemberEntity.getUserEntity().getName())
                .role(projectMemberEntity.getProjectRole())
                .userId(projectMemberEntity.getUserEntity().getId())
                .invitedAt(projectMemberEntity.getInvitedAt())
                .avatarUrl(projectMemberEntity.getUserEntity().getAvatarUrl())
                .build();
    }

    public MemberResponseDto fromProjectMemberEntity(ProjectEntity projectEntity) {
        return MemberResponseDto.builder()
                .email(projectEntity.getOwner().getEmail())
                .name(projectEntity.getOwner().getName())
                .role(ProjectRole.OWNER)
                .userId(projectEntity.getOwner().getId())
                .invitedAt(null)
                .avatarUrl(projectEntity.getOwner().getAvatarUrl())
                .build();
    }
}
