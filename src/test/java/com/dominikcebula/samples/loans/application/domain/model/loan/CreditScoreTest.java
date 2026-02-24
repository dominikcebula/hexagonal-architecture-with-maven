package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreditScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 500, 999, 1000})
    void shouldCreateCreditScoreWithValidValue(int validValue) {
        // when
        CreditScore creditScore = new CreditScore(validValue);

        // then
        assertThat(creditScore.getValue()).isEqualTo(validValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void shouldThrowExceptionWhenValueIsNegative(int invalidValue) {
        // when/then
        assertThatThrownBy(() -> new CreditScore(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Credit score must be greater than or equal to zero.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 2000})
    void shouldThrowExceptionWhenValueExceedsMaximum(int invalidValue) {
        // when/then
        assertThatThrownBy(() -> new CreditScore(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Credit score must be less than or equal to 1000.");
    }
}