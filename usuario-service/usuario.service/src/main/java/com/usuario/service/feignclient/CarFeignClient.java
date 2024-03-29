package com.usuario.service.feignclient;

import com.usuario.service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002")
public interface CarFeignClient {

    @PostMapping("/car")
    Car save(@RequestBody Car car);

    @GetMapping("car/usuario/{usuarioId}")
    List<Car> getCars(@PathVariable("usuarioId") int usuarioId);
}
