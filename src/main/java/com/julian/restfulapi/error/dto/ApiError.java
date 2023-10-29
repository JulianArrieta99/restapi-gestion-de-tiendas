package com.julian.restfulapi.error.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> errors;
    private String documentation;

    public ApiError() {
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }


    public ApiError(HttpStatus status, LocalDateTime timestamp, String message, Map<String, String> errors, String documentation) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
        this.documentation = documentation;
    }

    public ApiError(HttpStatus httpStatus, String errorDeIntegridadDeDatos, String message) {
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = LocalDateTime.parse(timestamp);
    }

    public Map<String, String> getErrors() {
        if (this.errors == null) {
            this.errors = new HashMap<>();
            this.errors.put("defaultError", "No se encontraron datos");
        }
        return this.errors;
    }


}
