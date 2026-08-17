package com.appbuilder.appbuilder.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectFileEntity {

    private String id;

    private ProjectEntity projectEntity;

    private String path;

    private String minioObjectKey;

    private UserEntity createdBy;

    private UserEntity updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
