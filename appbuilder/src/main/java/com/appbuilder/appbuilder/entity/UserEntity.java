package com.appbuilder.appbuilder.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserEntity {

    private String id;

    private String email;

    private String password;

    private String name;

    private String avatarUrl;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    LocalDateTime deletedAt;
}
