package com.dukachyov.carsdirectory.controllers;

import com.dukachyov.carsdirectory.dto.CarDTO;
import com.dukachyov.carsdirectory.dto.CarResponse;
import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.services.CarService;
import com.dukachyov.carsdirectory.validators.CarValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addCar(@RequestBody @Valid CarDTO carDTO,
                                             BindingResult bindingResult) {

        Car carToAdd = convertToCar(carDTO);

        carValidator.validate(carToAdd, bindingResult);
//        if (bindingResult.hasErrors())
//            returnErrorsToClient

        carService.addCar(carToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }


    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    private Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

}
