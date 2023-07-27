package com.dukachyov.carsdirectory.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "registration_number", unique = true)
    @NotEmpty
    @Pattern(regexp = "^\\w{2}\\d{3}\\w\\d{2}$",
            message = "Номер машины должен следовать паттерну: AA999A99")
    private String registrationNumber;

    @Column(name = "car_model")
    @NotEmpty
    @Length(max = 255, message = "Не должно быть больше 255 символов")
    private String carModel;

    @Column(name = "colour")
    @NotEmpty
    @Length(max = 255, message = "Не должно быть больше 255 символов")
    private String colour;

    @Column(name = "production_year")
    @NotNull
    @Min(value = 1850, message = "Год производства не должен быть раньше чем 1850")
    private int productionYear;

}
