package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CheckOutResponseDto {
    @JsonProperty("checkout_url")
    private String checkoutUrl;
}
