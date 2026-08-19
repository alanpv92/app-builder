package com.appbuilder.appbuilder.dto.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private Object data;
    private String error;
    private Boolean isSuccess;
    private LocalDateTime timestamp;


   public static  ApiResponse success(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setTimestamp(LocalDateTime.now());
        apiResponse.setIsSuccess(true);
        return apiResponse;
    }

  public   static  ApiResponse error(String error) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(error);
        apiResponse.setTimestamp(LocalDateTime.now());
        apiResponse.setIsSuccess(false);
        return apiResponse;
    }

}
