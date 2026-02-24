package com.dominikcebula.samples.loans.application.port.in.dto;

public record EmploymentDTO(Long id, String name, String industry, MoneyDTO yearlyIncome, int numberOfEmployees,
                            String email, String phoneNumber, String website) {
}
