package com.myweb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse <T> {
    boolean success;
    String message;
    T result;

}
