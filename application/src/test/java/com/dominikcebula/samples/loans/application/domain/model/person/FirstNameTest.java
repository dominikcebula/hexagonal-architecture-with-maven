package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstNameTest {

    @Test
    void shouldCreateFirstNameWithValidValue() {
        // given
        String validFirstName = "John";

        // when
        FirstName firstName = new FirstName(validFirstName);

        // then
        assertThat(firstName.getValue()).isEqualTo(validFirstName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new FirstName(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("First Name cannot be blank.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new FirstName(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("First Name cannot be blank.");
    }
}