package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlanRequestDto {


    @NotBlank(message = "name cannot be blank")
    private final String name;

    @NotNull(message = "max projects cannot be empty")
    @JsonProperty("max_projects")
    private final Integer maxProjects;

    @NotNull(message = "max tokens cannot be empty")
    @JsonProperty("max_tokens_per_day")
    private final Integer maxTokensPerDay;

    @NotNull(message = "max previews cannot be empty")
    @JsonProperty("max_previews")
    private final Integer maxPreviews;

    @JsonProperty("unlimited_ai")
    private final Boolean unlimitedAi;

    @NotBlank(message = "price Id cannot be empty")
    @JsonProperty("price_id")
    private String priceId;
}
