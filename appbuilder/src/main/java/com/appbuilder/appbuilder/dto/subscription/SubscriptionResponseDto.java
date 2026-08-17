package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionResponseDto {

    private  final  PlanResponseDto plan;
    private  final String status;
    @JsonProperty("period_end")
    private  final LocalDateTime periodEnd;
    @JsonProperty("tokens_used_this_cycle")
    private  final Long tokensUsedThisCycle;
}
