package com.dukachyov.carsdirectory.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
public class CarStat {

    @NotEmpty
    @Pattern(regexp = "^\\w{2}\\d{3}\\w\\d{2}$",
            message = "Номер машины должен следовать паттерну AA999A99")
    private String registrationNumber;

    @NotEmpty
    @Length(max = 255, message = "Не должно быть больше 255 символов")
    private String carModel;

    @NotEmpty
    @Length(max = 255, message = "Не должно быть больше 255 символов")
    private String colour;

    @NotNull
    @Min(value = 1850, message = "Год производства не должен быть раньше чем 1850")
    private int productionYear;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
