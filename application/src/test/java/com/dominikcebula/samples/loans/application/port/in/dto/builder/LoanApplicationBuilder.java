package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.loan.*;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.Period;

public class LoanApplicationBuilder {
    private Identifier identifier = Identifier.empty();
    private Applicant applicant = ApplicantBuilder.newApplicant().build();
    private LoanAmount amount = new LoanAmount(Money.of(new BigDecimal("30000"), "USD"));
    private TermsInMonths termsInMonths = new TermsInMonths(Period.ofYears(10));
    private LoanStatus status = LoanStatus.REGISTERED;

    private LoanApplicationBuilder() {
    }

    public static LoanApplicationBuilder newLoanApplication() {
        return new LoanApplicationBuilder();
    }

    public LoanApplicationBuilder withIdentifier(Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public LoanApplicationBuilder withApplicant(Applicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public LoanApplicationBuilder withAmount(LoanAmount amount) {
        this.amount = amount;
        return this;
    }

    public LoanApplicationBuilder withTermsInMonths(TermsInMonths termsInMonths) {
        this.termsInMonths = termsInMonths;
        return this;
    }

    public LoanApplicationBuilder withStatus(LoanStatus status) {
        this.status = status;
        return this;
    }

    public LoanApplication build() {
        return new LoanApplication(identifier, applicant, amount, termsInMonths, status);
    }
}