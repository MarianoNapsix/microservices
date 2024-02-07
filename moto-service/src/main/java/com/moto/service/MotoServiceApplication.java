package com.moto.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MotoServiceApplication {

    @Value("${server.port}")
    private int serverPort;

    @Value("${number.to-port}")
    private int numberToPort;

    public static void main(String[] args) {
        SpringApplication.run(MotoServiceApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("XXX - Number TO Port: " + numberToPort);
        System.out.println("XXX - Server port: " + serverPort);
    }
}
