package com.appbuilder.appbuilder.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PreviewEntity {

    private String id;

    private ProjectEntity projectEntity;

    private String namespace;

    private String podName;

    private  String previewUrl;

    private PreviewEntity status;

    private LocalDateTime startedTime;

    private LocalDateTime terminatedAt;

    private LocalDateTime createdAt;

}
