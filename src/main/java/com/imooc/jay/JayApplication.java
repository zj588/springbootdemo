package com.imooc.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayApplication.class, args);
    }
}
