package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AgeTest {

    @ParameterizedTest
    @ValueSource(longs = {1, 18, 50, 119})
    void shouldCreateAgeWithValidValue(long validValue) {
        // when
        Age age = new Age(validValue);

        // then
        assertThat(age.getValue()).isEqualTo((int) validValue);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void shouldThrowExceptionWhenValueIsNotPositive(long invalidValue) {
        // when/then
        assertThatThrownBy(() -> new Age(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Age cannot be negative.");
    }

    @ParameterizedTest
    @ValueSource(longs = {120, 121, 200})
    void shouldThrowExceptionWhenValueExceedsMaximum(long invalidValue) {
        // when/then
        assertThatThrownBy(() -> new Age(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Age cannot be greater than 120.");
    }

    @ParameterizedTest
    @ValueSource(longs = {18, 19, 50, 100})
    void shouldReturnTrueForAdultAge(long adultAge) {
        // when
        Age age = new Age(adultAge);

        // then
        assertThat(age.isAdult()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 5, 10, 17})
    void shouldReturnFalseForNonAdultAge(long nonAdultAge) {
        // when
        Age age = new Age(nonAdultAge);

        // then
        assertThat(age.isAdult()).isFalse();
    }
}