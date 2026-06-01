package com.sneakerhouse;

import com.sneakerhouse.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class SneakerHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SneakerHouseApplication.class, args);
    }
}
