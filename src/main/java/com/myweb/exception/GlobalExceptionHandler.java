package com.myweb.exception;

import com.myweb.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {

        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .success(false)
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){

        return ResponseEntity.badRequest().body((ApiResponse.builder()
                .success(false)
                .message(exception.getFieldError().getDefaultMessage())
                .build()));
    }
}
