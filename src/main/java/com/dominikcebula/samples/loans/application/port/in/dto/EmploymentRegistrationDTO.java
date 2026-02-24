package com.dominikcebula.samples.loans.application.port.in.dto;

public record EmploymentRegistrationDTO(
        String name,
        String industry,
        MoneyDTO yearlyIncome,
        int numberOfEmployees,
        String email,
        String phoneNumber,
        String website
) {
}
