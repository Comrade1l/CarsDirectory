package com.dukachyov.carsdirectory.exceptions;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(String msg) {
        super(msg);
    }
}
