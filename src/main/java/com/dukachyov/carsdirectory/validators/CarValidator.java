package com.dukachyov.carsdirectory.validators;

import com.dukachyov.carsdirectory.entities.Car;
import com.dukachyov.carsdirectory.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CarValidator implements Validator {

    private final CarService carService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Car car = (Car) target;

        if (carService.findCarByRegistrationNumber(car.getRegistrationNumber()).isPresent())
            errors.rejectValue("registrationNumber", "",
                    "Машина с такими регистрационными номерами уже существует");
    }
}
