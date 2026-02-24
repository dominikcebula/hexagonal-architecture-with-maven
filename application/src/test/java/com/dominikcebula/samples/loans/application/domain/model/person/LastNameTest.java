package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastNameTest {

    @Test
    void shouldCreateLastNameWithValidValue() {
        // given
        String validLastName = "Smith";

        // when
        LastName lastName = new LastName(validLastName);

        // then
        assertThat(lastName.getValue()).isEqualTo(validLastName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new LastName(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Last Name cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new LastName(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Last Name cannot be empty.");
    }
}