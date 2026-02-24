package com.dominikcebula.samples.loans.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dominikcebula.samples.loans"})
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class);
    }
}
