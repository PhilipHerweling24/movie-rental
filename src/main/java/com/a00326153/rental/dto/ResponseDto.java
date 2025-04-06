package com.a00326153.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private int code;
    private Object data;

    public ResponseDto(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
