package com.julian.restfulapi.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private int errorCode;
    private String errorMessage;
    private List<String> details; // Para almacenar mensajes de detalles adicionales


}
