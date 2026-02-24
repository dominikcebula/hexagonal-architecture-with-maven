package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Period;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TermsInMonthsTest {

    @Test
    void shouldCreateTermsInMonthsWithValidPeriod() {
        // given
        Period validPeriod = Period.ofYears(5);
        int expectedMonths = 60; // 5 years = 60 months

        // when
        TermsInMonths termsInMonths = new TermsInMonths(validPeriod);

        // then
        assertThat(termsInMonths.getValue()).isEqualTo(expectedMonths);
    }

    @ParameterizedTest
    @ValueSource(longs = {2, 12, 24, 120, 359})
    void shouldCreateTermsInMonthsWithValidValue(long validValue) {
        // when
        TermsInMonths termsInMonths = new TermsInMonths(validValue);

        // then
        assertThat(termsInMonths.getValue()).isEqualTo((int) validValue);
    }

    @ParameterizedTest
    @ValueSource(longs = {0})
    void shouldThrowExceptionWhenValueIsNotGreaterThanMinimum(long invalidValue) {
        // when/then
        assertThatThrownBy(() -> new TermsInMonths(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Loan must be given for at least one month.");
    }

    @ParameterizedTest
    @ValueSource(longs = {361, 500})
    void shouldThrowExceptionWhenValueExceedsMaximum(long invalidValue) {
        // when/then
        assertThatThrownBy(() -> new TermsInMonths(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Loan must be given for a maximum period of 360 months.");
    }
}