package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.port.in.dto.ApplicantRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.MoneyDTO;

public class LoanApplicationRegistrationDTOBuilder {

    private ApplicantRegistrationDTO applicant = ApplicantRegistrationDTOBuilder.newApplicantRegistration().build();
    private MoneyDTO amount = new MoneyDTOBuilder().build();
    private int termsInMonths = 36;

    private LoanApplicationRegistrationDTOBuilder() {
    }

    public static LoanApplicationRegistrationDTOBuilder newLoanApplicationRegistration() {
        return new LoanApplicationRegistrationDTOBuilder();
    }

    public LoanApplicationRegistrationDTOBuilder withApplicant(ApplicantRegistrationDTO applicant) {
        this.applicant = applicant;
        return this;
    }

    public LoanApplicationRegistrationDTOBuilder withAmount(MoneyDTO amount) {
        this.amount = amount;
        return this;
    }

    public LoanApplicationRegistrationDTOBuilder withTermsInMonths(int termsInMonths) {
        this.termsInMonths = termsInMonths;
        return this;
    }

    public LoanApplicationRegistrationDTO build() {
        return new LoanApplicationRegistrationDTO(applicant, amount, termsInMonths);
    }
}
