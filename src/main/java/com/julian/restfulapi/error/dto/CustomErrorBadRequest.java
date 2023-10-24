package com.julian.restfulapi.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorBadRequest {
    private int errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
    private String trace;

}
