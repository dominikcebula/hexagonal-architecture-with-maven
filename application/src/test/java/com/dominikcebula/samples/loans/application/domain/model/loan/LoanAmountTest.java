package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LoanAmountTest {

    @Test
    void shouldCreateLoanAmountWithValidValue() {
        // given
        Money validAmount = Money.of(new BigDecimal("1000.00"), "USD");

        // when
        LoanAmount loanAmount = new LoanAmount(validAmount);

        // then
        assertThat(loanAmount.getValue()).isEqualTo(validAmount);
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new LoanAmount(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Loan amount cannot be null.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsZero() {
        // given
        Money zeroAmount = Money.of(BigDecimal.ZERO, "USD");

        // when/then
        assertThatThrownBy(() -> new LoanAmount(zeroAmount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Loan amount must be positive.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        // given
        Money negativeAmount = Money.of(new BigDecimal("-100.00"), "USD");

        // when/then
        assertThatThrownBy(() -> new LoanAmount(negativeAmount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Loan amount must be positive.");
    }
}