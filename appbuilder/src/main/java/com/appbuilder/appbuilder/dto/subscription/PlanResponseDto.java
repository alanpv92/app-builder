package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlanResponseDto {

    private final String id;
    private final String name;
    @JsonProperty("max_projects")
    private final Integer maxProjects;
    @JsonProperty("max_tokens_per_day")
    private final Integer maxTokensPerDay;
    @JsonProperty("unlimited_ai")
    private final Boolean unlimitedAi;
    private final String price;
}
