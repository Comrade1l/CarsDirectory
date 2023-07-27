package com.dukachyov.carsdirectory.controllers;

import com.dukachyov.carsdirectory.dto.CarStat;
import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.services.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatisticController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String allStat() {
        return "Количество записей в базе данных: " + carService.getAllCars().size() +
                "\n Первая запись: " +
                convertToCarStat(carService.getAllCarsOrderByDate().stream().findFirst().get()) +
                "\n Последняя запись: " +
                convertToCarStat(carService.getAllCarsOrderByDate().stream().reduce((first, second) -> second).get());
    }

    @GetMapping("/count")
    public int allCars() {
        return carService.getAllCars().size();
    }

    @GetMapping("/firstRecord")
    public CarStat firstRecord() {
        return convertToCarStat(carService.getAllCarsOrderByDate().stream().findFirst().get());
    }

    @GetMapping("/lastRecord")
    public CarStat lastRecord() {
       return convertToCarStat(carService.getAllCarsOrderByDate().stream().reduce((first, second) -> second).get());
    }

    private CarStat convertToCarStat(Car car) {
        return modelMapper.map(car, CarStat.class);
    }
}
