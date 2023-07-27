package com.dukachyov.carsdirectory.services;

import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAllByOrderByRegistrationNumber();
    }

    public Car getCar(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        return foundCar.orElse(null);
    }

    @Transactional
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void deleteCar(int id) {
        carRepository.deleteCarById(id);
    }

    @Transactional
    public void deleteCarByRegNumber(String regNumber) {
        carRepository.deleteCarByRegistrationNumber(regNumber);
    }

    // Для валидации
    public Optional<Car> getCarByRegistrationNumber(String regNumber) {
        return carRepository.findCarByRegistrationNumber(regNumber);
    }

    public List<Car> getAllCarsOrderByDate() {
        return carRepository.findCarByOrderByCreatedAt();
    }
}
