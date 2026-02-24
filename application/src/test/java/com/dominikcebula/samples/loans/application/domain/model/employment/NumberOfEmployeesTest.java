package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberOfEmployeesTest {

    @Test
    void shouldCreateNumberOfEmployeesWithValidValue() {
        // when
        NumberOfEmployees numberOfEmployees = new NumberOfEmployees(100);

        // then
        assertThat(numberOfEmployees.getValue()).isEqualTo(100);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void shouldThrowExceptionWhenValueIsNotGreaterThanZero(int invalidValue) {
        // when/then
        assertThatThrownBy(() -> new NumberOfEmployees(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Number of employees must be greater than zero.");
    }

    @Test
    void shouldThrowExceptionWhenValueExceedsReasonableRange() {
        // given
        int invalidValue = 5_000_000;

        // when/then
        assertThatThrownBy(() -> new NumberOfEmployees(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Number of employees must be within a reasonable range.");
    }
}
