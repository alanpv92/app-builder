package com.appbuilder.appbuilder.entity;

import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_members")
public class ProjectMemberEntity {

    @EmbeddedId
    private ProjectMemberId projectMemberId;


    @ManyToOne
    @MapsId("projectId")
    private ProjectEntity projectEntity;

    @ManyToOne
    @MapsId("userId")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectRole projectRole;

    private LocalDateTime invitedAt;

    private LocalDateTime acceptedAt;


}
