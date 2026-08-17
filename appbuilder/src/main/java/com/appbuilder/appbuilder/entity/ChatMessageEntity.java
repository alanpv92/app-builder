package com.appbuilder.appbuilder.entity;

import com.appbuilder.appbuilder.entity.enums.MessageRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageEntity {

    private String id;

    private ChatSessionEntity chatSessionEntity;

    private String content;

    private MessageRole  messageRole;

    private String toolCalls;

    private Integer tokensUsed;

    private LocalDateTime createdAt;

}
