package com.dominikcebula.samples.loans.application.port.in.dto;

public record LoanApplicationRegistrationDTO(
        ApplicantRegistrationDTO applicant,
        MoneyDTO amount,
        int termsInMonths
) {
}
