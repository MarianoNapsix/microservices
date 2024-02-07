package com.moto.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/actuator/info")
    public String holaMundo() {
        return "Hola Mundo!!";
    }

}
