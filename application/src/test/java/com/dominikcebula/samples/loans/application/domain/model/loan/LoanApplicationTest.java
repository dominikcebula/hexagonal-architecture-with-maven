package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.employment.NumberOfEmployees;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.ApplicantBuilder;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.EmploymentBuilder;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationBuilder;
import com.dominikcebula.samples.loans.application.port.time.MockDateProvider;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication.LoanApprovalResult;
import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class LoanApplicationTest {
    @BeforeEach
    public void setUp() {
        MockDateProvider.setUp();
    }

    @AfterEach
    public void tearDown() {
        MockDateProvider.tearDown();
    }

    @Test
    void shouldCreateNewLoanInRegisteredStatus() {
        // when
        LoanApplication loanApplication = new LoanApplication(
                ApplicantBuilder.newApplicant().build(),
                new LoanAmount(Money.of(new BigDecimal(100000), "USD")),
                new TermsInMonths(Period.ofYears(10)));

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(REGISTERED);
    }

    @Test
    void shouldCreateNewLoanWithoutIdentifier() {
        // when
        LoanApplication loanApplication = new LoanApplication(
                ApplicantBuilder.newApplicant().build(),
                new LoanAmount(Money.of(new BigDecimal(100000), "USD")),
                new TermsInMonths(Period.ofYears(10)));

        // then
        assertThat(loanApplication.getIdentifier().getValue()).isEmpty();
    }

    @Test
    void shouldCreateNewLoanWithIdentifier() {
        // when
        final long id = 555;
        LoanApplication loanApplication = new LoanApplication(
                new Identifier(id),
                ApplicantBuilder.newApplicant().build(),
                new LoanAmount(Money.of(new BigDecimal(100000), "USD")),
                new TermsInMonths(Period.ofYears(10)),
                LoanStatus.REGISTERED);

        // then
        assertThat(loanApplication.getIdentifier().getValue()).isEqualTo(Optional.of(id));
    }

    @Test
    void shouldApproveLoan() {
        // given
        LoanApplication loanApplication = LoanApplicationBuilder.newLoanApplication().build();

        // when
        LoanApprovalResult approvalResult = loanApplication.approve();

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(APPROVED);
        assertThat(approvalResult.getStatus()).isEqualTo(APPROVED);
        assertThat(approvalResult.getValidationResults().isSuccessful()).isTrue();
        assertThat(approvalResult.getValidationResults().getMessages()).isEmpty();
    }

    @Test
    void shouldRejectLoanForUnderageApplicant() {
        // given
        LoanApplication loanApplication = LoanApplicationBuilder.newLoanApplication()
                .withApplicant(
                        ApplicantBuilder.newApplicant()
                                .withBirthDate(new BirthDate(birthDateForAgeOfFive()))
                                .build()
                )
                .build();

        // when
        LoanApprovalResult approvalResult = loanApplication.approve();

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getValidationResults().isSuccessful()).isFalse();
        assertThat(approvalResult.getValidationResults().getMessages())
                .contains("Applicant is not of legal age.");
    }

    @Test
    void shouldRejectLoanForInsufficientCreditScore() {
        // given
        LoanApplication loanApplication = LoanApplicationBuilder.newLoanApplication()
                .withApplicant(
                        ApplicantBuilder.newApplicant()
                                .withCreditScore(new CreditScore(600))
                                .build()
                )
                .build();

        // when
        LoanApprovalResult approvalResult = loanApplication.approve();

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getValidationResults().isSuccessful()).isFalse();
        assertThat(approvalResult.getValidationResults().getMessages())
                .contains("Applicant does not has sufficient credit score.");
    }

    @Test
    void shouldRejectLoanForUnstableEmployment() {
        // given
        LoanApplication loanApplication = LoanApplicationBuilder.newLoanApplication()
                .withApplicant(
                        ApplicantBuilder.newApplicant()
                                .withEmployment(
                                        EmploymentBuilder.newEmployment()
                                                .withNumberOfEmployees(new NumberOfEmployees(2000))
                                                .build()
                                )
                                .build()
                )
                .build();

        // when
        LoanApprovalResult approvalResult = loanApplication.approve();

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getValidationResults().isSuccessful()).isFalse();
        assertThat(approvalResult.getValidationResults().getMessages())
                .contains("Applicant does not have stable employer.");
    }

    @Test
    void shouldRejectLoanForInsufficientIncome() {
        // given
        LoanApplication loanApplication = LoanApplicationBuilder.newLoanApplication()
                .withTermsInMonths(new TermsInMonths(Period.ofYears(10)))
                .withAmount(new LoanAmount(Money.of(new BigDecimal(120000), "USD")))
                .withApplicant(
                        ApplicantBuilder.newApplicant()
                                .withEmployment(
                                        EmploymentBuilder.newEmployment()
                                                .withYearlyIncome(Money.of(new BigDecimal(3000), "USD"))
                                                .build()
                                )
                                .build()
                )
                .build();

        // when
        LoanApprovalResult approvalResult = loanApplication.approve();

        // then
        assertThat(loanApplication.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getStatus()).isEqualTo(REJECTED);
        assertThat(approvalResult.getValidationResults().isSuccessful()).isFalse();
        assertThat(approvalResult.getValidationResults().getMessages())
                .contains("Applicant does not have sufficient income for a loan.");
    }

    private LocalDate birthDateForAgeOfFive() {
        return new MockDateProvider().now().minusYears(5);
    }
}
