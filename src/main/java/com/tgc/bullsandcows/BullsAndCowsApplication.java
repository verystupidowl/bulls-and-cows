package com.tgc.bullsandcows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class BullsAndCowsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BullsAndCowsApplication.class, args);
    }
}
