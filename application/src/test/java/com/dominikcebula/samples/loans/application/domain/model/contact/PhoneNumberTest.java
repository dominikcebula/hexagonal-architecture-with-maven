package com.dominikcebula.samples.loans.application.domain.model.contact;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhoneNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567890",
            "+1 (123) 456-7890",
            "+44 123 456 7890",
            "123-456-7890",
            "123.456.7890",
            "123 456 7890",
            "+1-123-456-7890",
            "+1 123 456 7890 ext 123",
            "+1 123 456 7890 x123"
    })
    void shouldCreatePhoneNumberWithValidValue(String validPhoneNumber) {
        // when
        PhoneNumber phoneNumber = new PhoneNumber(validPhoneNumber);

        // then
        assertThat(phoneNumber.getValue()).isEqualTo(validPhoneNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new PhoneNumber(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Phone number cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new PhoneNumber(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Phone number cannot be empty.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc",
            "123abc",
            "++123456789",
            "123-456-789a",
            "123 456 789 ext abc",
            "12",
            "1"
    })
    void shouldThrowExceptionWhenValueIsNotValidPhoneNumber(String invalidPhoneNumber) {
        // when/then
        assertThatThrownBy(() -> new PhoneNumber(invalidPhoneNumber))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Phone number value is not a valid phone number.");
    }
}