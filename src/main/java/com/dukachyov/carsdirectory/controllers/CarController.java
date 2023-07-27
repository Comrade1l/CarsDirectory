package com.dukachyov.carsdirectory.controllers;

import com.dukachyov.carsdirectory.dto.CarDTO;
import com.dukachyov.carsdirectory.dto.CarResponse;
import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.exceptions.CarErrorResponse;
import com.dukachyov.carsdirectory.exceptions.CarException;
import com.dukachyov.carsdirectory.exceptions.CarNotFoundException;
import com.dukachyov.carsdirectory.services.CarService;
import com.dukachyov.carsdirectory.validators.CarValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.dukachyov.carsdirectory.exceptions.ErrorsUtil.returnErrorsToClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarValidator carValidator;
    private final ModelMapper modelMapper;


    @GetMapping
    public CarResponse getCars() {
        return new CarResponse(carService.getAllCars().stream()
                .map(this::convertToCarDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable int id) {
        Car car = carService.getCar(id);
        if (car == null){
            throw new CarNotFoundException("Машины с таким id не существует");
        }
        return convertToCarDTO(carService.getCar(id));
    }

    @PostMapping("/add")
    public String addCar(@RequestBody @Valid CarDTO carDTO,
                                             BindingResult bindingResult) {

        Car carToAdd = convertToCar(carDTO);

        carValidator.validate(carToAdd, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        carService.addCar(carToAdd);
        return "Машина с регистрационными номерами " + carToAdd.getRegistrationNumber() + " успешно зарегистрирована";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable int id) {
       Car car = carService.getCar(id);
        if (car == null){
            throw new CarNotFoundException("Машины с таким id не существует");
        }
        carService.deleteCar(id);
        return "Машина с id = " + id + " удалена";
    }

    @DeleteMapping("/deleteByRegNumber/{regNumber}")
    public String deleteCarByRegNumber(@PathVariable String regNumber) {
        Optional<Car> car = carService.getCarByRegistrationNumber(regNumber);
        if (car.isEmpty()){
            throw new CarNotFoundException("Машины с такими регистрационным номером не существует");
        }
        carService.deleteCarByRegNumber(regNumber);
        return "Машина с регистрационным номером " + regNumber + " удалена";
    }

    @ExceptionHandler
    private ResponseEntity<CarErrorResponse> handleException(CarException e) {
        CarErrorResponse response = new CarErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CarErrorResponse> handleException(CarNotFoundException e) {
        CarErrorResponse response = new CarErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    private Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

}
