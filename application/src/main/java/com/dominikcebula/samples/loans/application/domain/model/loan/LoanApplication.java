package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.ValidationResult;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.ValidationResults;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.javamoney.moneta.Money;

import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus.APPROVED;
import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus.REJECTED;
import static com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation.requireValueMatchingCondition;

@Getter
@EqualsAndHashCode
@ToString
public class LoanApplication {
    private final Identifier identifier;
    private final Applicant applicant;
    private final LoanAmount amount;
    private final TermsInMonths termsInMonths;
    private LoanStatus status;

    public LoanApplication(Applicant applicant, LoanAmount amount, TermsInMonths termsInMonths) {
        this(Identifier.empty(), applicant, amount, termsInMonths, LoanStatus.REGISTERED);
    }

    public LoanApplication(Identifier identifier, Applicant applicant, LoanAmount amount, TermsInMonths termsInMonths, LoanStatus status) {
        Validation.requireNonNull(identifier, "identifier is required.");
        Validation.requireNonNull(applicant, "applicant is required.");
        Validation.requireNonNull(amount, "amount is required.");
        Validation.requireNonNull(termsInMonths, "term in months is required.");
        Validation.requireNonNull(status, "status is required.");

        this.identifier = identifier;
        this.applicant = applicant;
        this.amount = amount;
        this.termsInMonths = termsInMonths;
        this.status = status;
    }

    public LoanApprovalResult approve() {
        ValidationResults validationResults = validateLoanCanBeApproved();

        if (validationResults.isSuccessful())
            status = APPROVED;
        else
            status = REJECTED;

        return new LoanApprovalResult(status, validationResults);
    }

    private ValidationResults validateLoanCanBeApproved() {
        return ValidationResults.of(
                validateIsOfLegalAge(),
                validateHasSufficientCreditScore(),
                validateHasStableEmployment(),
                validateHasSufficientIncome());
    }

    private ValidationResult validateIsOfLegalAge() {
        return ValidationResult.of(
                () -> requireValueMatchingCondition(applicant.getAge().isAdult(), "Applicant is not of legal age."));
    }

    private ValidationResult validateHasSufficientCreditScore() {
        return ValidationResult.of(
                () -> requireValueMatchingCondition(applicant.getCreditScore().getValue() > 600, "Applicant does not has sufficient credit score."));
    }

    private ValidationResult validateHasStableEmployment() {
        return ValidationResult.of(
                () -> requireValueMatchingCondition(applicant.getEmployment().isStableEmployer(),
                        "Applicant does not have stable employer."));
    }

    private ValidationResult validateHasSufficientIncome() {
        Money monthlyLoanPayment = calculateSimplifiedMonthlyPayment();
        Money loanPaymentsForOneQuarter = monthlyLoanPayment.multiply(3);

        return ValidationResult.of(
                () -> requireValueMatchingCondition(
                        applicant.getEmployment().getYearlyIncome().isGreaterThan(loanPaymentsForOneQuarter),
                        "Applicant does not have sufficient income for a loan."));
    }

    private Money calculateSimplifiedMonthlyPayment() {
        return amount.getValue().divide(termsInMonths.getValue());
    }

    @RequiredArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class LoanApprovalResult {
        private final LoanStatus status;
        private final ValidationResults validationResults;
    }
}
