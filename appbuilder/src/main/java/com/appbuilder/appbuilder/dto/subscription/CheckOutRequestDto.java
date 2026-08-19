package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckOutRequestDto {

    @NotBlank(message = "plan id cannot be blank")
    @JsonProperty("plan_id")
    private final String planId;
}
