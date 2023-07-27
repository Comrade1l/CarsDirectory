package com.dukachyov.carsdirectory.repositories;

import com.dukachyov.carsdirectory.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Optional<Car> findCarByRegistrationNumber(String regNumber);

    List<Car> findAllByOrderByRegistrationNumber();

    void deleteCarById(int id);

    void deleteCarByRegistrationNumber(String regNumber);

    List<Car> findCarByOrderByCreatedAt();

}
