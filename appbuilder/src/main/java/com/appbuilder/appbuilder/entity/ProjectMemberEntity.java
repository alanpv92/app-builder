package com.appbuilder.appbuilder.entity;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;

import java.time.LocalDateTime;

public class ProjectMemberEntity {

    private ProjectMemberId projectMemberId;

    private ProjectEntity projectEntity;

    private UserEntity userEntity;

    private ProjectRole projectRole;

    private LocalDateTime invitedAt;

    private LocalDateTime acceptedAt;


}
