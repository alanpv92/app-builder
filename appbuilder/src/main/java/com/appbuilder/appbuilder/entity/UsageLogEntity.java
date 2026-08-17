package com.appbuilder.appbuilder.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsageLogEntity {

    private String id;

    private UserEntity userEntity;

    private ProjectEntity projectEntity;

    private String action;

    private Integer tokensUsed;

    private Integer durationMs;

    private String metaData;

    private LocalDateTime createdAt;
}
