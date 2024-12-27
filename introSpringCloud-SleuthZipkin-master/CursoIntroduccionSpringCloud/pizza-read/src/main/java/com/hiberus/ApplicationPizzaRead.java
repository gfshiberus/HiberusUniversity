package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApplicationPizzaRead {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPizzaRead.class,args);
    }
}