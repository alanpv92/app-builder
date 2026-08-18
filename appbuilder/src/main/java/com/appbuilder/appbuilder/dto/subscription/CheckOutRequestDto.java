package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckOutRequestDto {

    @JsonProperty("plan_id")
    private final String planId;
}
