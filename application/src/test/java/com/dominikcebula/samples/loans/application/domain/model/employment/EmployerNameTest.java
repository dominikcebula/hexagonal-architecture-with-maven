package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmployerNameTest {

    @Test
    void shouldCreateEmployerNameWithValidValue() {
        // given
        String validEmployerName = "Acme Corporation";

        // when
        EmployerName employerName = new EmployerName(validEmployerName);

        // then
        assertThat(employerName.getValue()).isEqualTo(validEmployerName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new EmployerName(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Employer name cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new EmployerName(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Employer name cannot be empty.");
    }
}
