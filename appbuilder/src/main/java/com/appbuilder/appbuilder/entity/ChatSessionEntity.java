package com.appbuilder.appbuilder.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ChatSessionEntity {

    private ProjectEntity projectEntity;

    private UserEntity userEntity;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
