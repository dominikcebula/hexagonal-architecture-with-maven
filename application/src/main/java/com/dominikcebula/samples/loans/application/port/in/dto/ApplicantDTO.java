package com.dominikcebula.samples.loans.application.port.in.dto;

import java.time.LocalDate;

public record ApplicantDTO(Long id, String firstName, String lastName, LocalDate birthDate, int creditScore,
                           EmploymentDTO employment, String email, String phoneNumber) {
}
