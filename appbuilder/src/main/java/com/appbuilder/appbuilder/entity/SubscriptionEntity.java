package com.appbuilder.appbuilder.entity;

import com.appbuilder.appbuilder.entity.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubscriptionEntity {

    private String id;

    private UserEntity user;

    private PlanEntity plan;

    private SubscriptionStatus status;

    private String stripeCustomId;

    private String stripeSubscriptionId;

    private LocalDateTime currentPeriodStart;

    private LocalDateTime currentPeriodEnd;

    private  Boolean cancelAtPeriodEnd=false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
