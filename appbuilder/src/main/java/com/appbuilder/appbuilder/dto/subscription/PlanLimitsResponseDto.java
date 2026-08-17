package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlanLimitsResponseDto {
    @JsonProperty("plan_name")
    private final  String planName;
    @JsonProperty("max_tokens_per_day")
    private final  Integer maxTokensPerDay;
    @JsonProperty("max_projects")
    private final  Integer maxProjects;
    @JsonProperty("unlimited_ai")
    private final  boolean unlimitedAi;
}
