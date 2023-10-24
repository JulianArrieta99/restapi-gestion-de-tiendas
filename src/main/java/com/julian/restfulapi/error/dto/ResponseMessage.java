package com.julian.restfulapi.error.dto;

import lombok.*;


@NoArgsConstructor  @ToString @Getter @Setter
public class ResponseMessage <T> {
    private String message;
    private T data;

    public ResponseMessage(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
