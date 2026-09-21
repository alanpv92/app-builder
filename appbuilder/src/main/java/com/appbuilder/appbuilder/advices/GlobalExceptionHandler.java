package com.appbuilder.appbuilder.advices;


import com.appbuilder.appbuilder.dto.api.ApiResponse;
import com.appbuilder.appbuilder.exceptions.AuthenticatoinException;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.exceptions.ResourceNotFoundException;
import com.appbuilder.appbuilder.exceptions.UnAuthorizedAccessException;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import org.hibernate.ResourceClosedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        System.out.println("ResourceNotFoundException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException ex) {
        System.out.println("BadRequestException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println("MethodArgumentNotValidException: " + ex.getMessage());
        String error=ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error));
    }

    @ExceptionHandler(AuthenticatoinException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticatoinException ex) {
        System.out.println("handleAuthenticationException: " + ex.getMessage());
        if(ex.getMessage().equals(ErrorMessageConstants.SOMETHING_WENT_WRONG)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(ex.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<ApiResponse> handleUnAuthorizedAccessException(UnAuthorizedAccessException ex) {
        System.out.println("UnAuthorizedAccessException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(ex.getMessage()));
    }



}
