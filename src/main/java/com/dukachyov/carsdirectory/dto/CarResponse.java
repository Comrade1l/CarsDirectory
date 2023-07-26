package com.dukachyov.carsdirectory.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarResponse {

    private List<CarDTO> cars;

    public CarResponse(List<CarDTO> cars) {
        this.cars = cars;
    }
}
