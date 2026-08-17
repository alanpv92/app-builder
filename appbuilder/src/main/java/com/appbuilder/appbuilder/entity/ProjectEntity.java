package com.appbuilder.appbuilder.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectEntity {

    private String id;

    private String name;

    private UserEntity user;

    private  Boolean isPublic=false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
