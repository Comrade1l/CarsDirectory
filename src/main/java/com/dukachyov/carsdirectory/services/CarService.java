package com.dukachyov.carsdirectory.services;

import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Transactional
    public void register(Car car) {
        carRepository.save(car);
    }

}
