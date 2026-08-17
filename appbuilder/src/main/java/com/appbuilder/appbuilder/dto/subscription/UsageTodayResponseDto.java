package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsageTodayResponseDto {
    @JsonProperty("tokens_used")
    private  final int tokensUsed;
    @JsonProperty("tokens_limit")
    private  final int tokensLimit;
    @JsonProperty("previews_running")
    private  final int previewsRunning;
    @JsonProperty("previews_limit")
    private  final int previewsLimit;
}
