package com.dominikcebula.samples.loans.application.port.in.dto;

import java.time.LocalDate;

public record ApplicantRegistrationDTO(
        String firstName,
        String lastName,
        LocalDate birthDate,
        int creditScore,
        EmploymentRegistrationDTO employment,
        String email,
        String phoneNumber
) {
}
