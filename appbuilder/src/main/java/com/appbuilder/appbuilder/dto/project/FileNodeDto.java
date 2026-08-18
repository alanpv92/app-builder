package com.appbuilder.appbuilder.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileNodeDto {
    private final String path;
    @JsonProperty("modified_at")
    private final LocalDateTime modifiedAt;
    private final Long size;
    private final String type;

}
