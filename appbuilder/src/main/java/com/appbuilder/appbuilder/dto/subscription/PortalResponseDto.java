package com.appbuilder.appbuilder.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PortalResponseDto {
    @JsonProperty("portal_url")
    private final String portalUrl;
}
