package com.dominikcebula.samples.loans.application.domain.model.contact;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "user@example.com",
            "user.name@example.com",
            "user+tag@example.com",
            "user-name@example.com",
            "user_name@example.com",
            "user123@example.com",
            "user@subdomain.example.com",
            "user@example.co.uk"
    })
    void shouldCreateEmailWithValidValue(String validEmail) {
        // when
        Email email = new Email(validEmail);

        // then
        assertThat(email.getValue()).isEqualTo(validEmail);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new Email(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Email cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new Email(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Email cannot be empty.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "user",
            "user@",
            "@example.com",
            "user@example",
            "user@.com",
            "user@example.",
            "user@example.c",
            "user name@example.com",
    })
    void shouldThrowExceptionWhenValueIsNotValidEmail(String invalidEmail) {
        // when/then
        assertThatThrownBy(() -> new Email(invalidEmail))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Email value is not a valid email.");
    }
}