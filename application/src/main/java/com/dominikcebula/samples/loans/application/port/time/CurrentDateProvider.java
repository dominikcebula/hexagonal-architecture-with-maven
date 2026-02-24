package com.dominikcebula.samples.loans.application.port.time;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrentDateProvider {
    public LocalDate now() {
        return LocalDate.now();
    }
}
