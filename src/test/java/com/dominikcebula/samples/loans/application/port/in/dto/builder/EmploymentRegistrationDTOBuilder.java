package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.port.in.dto.EmploymentRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.MoneyDTO;

import java.math.BigDecimal;

public class EmploymentRegistrationDTOBuilder {

    private String name = "Acme Corp";
    private String industry = "Technology";
    private MoneyDTO yearlyIncome = new MoneyDTOBuilder().withAmount(BigDecimal.valueOf(85000)).build();
    private int numberOfEmployees = 5000;
    private String email = "hr@acmecorp.com";
    private String phoneNumber = "5551234567";
    private String website = "https://www.acmecorp.com";

    private EmploymentRegistrationDTOBuilder() {
    }

    public static EmploymentRegistrationDTOBuilder newEmploymentRegistration() {
        return new EmploymentRegistrationDTOBuilder();
    }

    public EmploymentRegistrationDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withYearlyIncome(MoneyDTO yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmploymentRegistrationDTOBuilder withWebsite(String website) {
        this.website = website;
        return this;
    }

    public EmploymentRegistrationDTO build() {
        return new EmploymentRegistrationDTO(name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);
    }
}
