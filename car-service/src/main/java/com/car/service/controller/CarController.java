package com.car.service.controller;

import com.car.service.entity.Car;
import com.car.service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listarCars() {
        List<Car> cars = carService.getAll();
        if (Objects.isNull(cars)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> obtenerCar(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (Objects.isNull(car)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> guardarCar(@RequestBody Car car) {
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Car>> listarCarsByUsuarioId(@PathVariable("id") int id) {
        List<Car> cars = carService.byUsuarioId(id);
        if (Objects.isNull(cars)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
}
