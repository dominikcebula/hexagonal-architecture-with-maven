package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.port.in.dto.ApplicantRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.EmploymentRegistrationDTO;

import java.time.LocalDate;

import static java.time.Month.AUGUST;

public class ApplicantRegistrationDTOBuilder {

    private String firstName = "John";
    private String lastName = "Doe";
    private LocalDate birthDate = LocalDate.of(1993, AUGUST, 15);
    private int creditScore = 700;
    private EmploymentRegistrationDTO employment = EmploymentRegistrationDTOBuilder.newEmploymentRegistration().build();
    private String email = "john.doe@example.com";
    private String phoneNumber = "1234567890";

    private ApplicantRegistrationDTOBuilder() {
    }

    public static ApplicantRegistrationDTOBuilder newApplicantRegistration() {
        return new ApplicantRegistrationDTOBuilder();
    }

    public ApplicantRegistrationDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withCreditScore(int creditScore) {
        this.creditScore = creditScore;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withEmployment(EmploymentRegistrationDTO employment) {
        this.employment = employment;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ApplicantRegistrationDTOBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ApplicantRegistrationDTO build() {
        return new ApplicantRegistrationDTO(firstName, lastName, birthDate, creditScore, employment, email, phoneNumber);
    }
}
