package com.example.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Esta línea es la que lanza toda la magia de Spring Boot
        SpringApplication.run(DemoApplication.class, args);
    }

}
