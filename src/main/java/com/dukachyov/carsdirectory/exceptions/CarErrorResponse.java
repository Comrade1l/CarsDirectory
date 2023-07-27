package com.dukachyov.carsdirectory.exceptions;

import lombok.Data;

@Data
public class CarErrorResponse {

    private String message;

    public CarErrorResponse(String message) {
        this.message = message;
    }
}
