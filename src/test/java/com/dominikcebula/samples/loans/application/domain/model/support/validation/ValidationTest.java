package com.dominikcebula.samples.loans.application.domain.model.support.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidationTest {

    private static final String TEST_MESSAGE = "Test validation message";
    private static final Pattern TEST_PATTERN = Pattern.compile("^[a-z]+$");

    @Test
    void shouldPassWhenObjectIsNotNull() {
        // when/then
        assertThatCode(() -> Validation.requireNonNull("not null", TEST_MESSAGE))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExceptionWhenObjectIsNull() {
        // when/then
        assertThatThrownBy(() -> Validation.requireNonNull(null, TEST_MESSAGE))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(TEST_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "text", "not blank"})
    void shouldPassWhenStringIsNotBlank(String notBlankValue) {
        // when/then
        assertThatCode(() -> Validation.requireNonBlank(notBlankValue, TEST_MESSAGE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    void shouldThrowExceptionWhenStringIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> Validation.requireNonBlank(blankValue, TEST_MESSAGE))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(TEST_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenStringIsNull() {
        // when/then
        assertThatThrownBy(() -> Validation.requireNonBlank(null, TEST_MESSAGE))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(TEST_MESSAGE);
    }

    @Test
    void shouldPassWhenConditionIsTrue() {
        // when/then
        assertThatCode(() -> Validation.requireValueMatchingCondition(true, TEST_MESSAGE))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExceptionWhenConditionIsFalse() {
        // when/then
        assertThatThrownBy(() -> Validation.requireValueMatchingCondition(false, TEST_MESSAGE))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(TEST_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "test"})
    void shouldPassWhenValueMatchesPattern(String matchingValue) {
        // when/then
        assertThatCode(() -> Validation.requireValueMatchingPattern(matchingValue, TEST_PATTERN, TEST_MESSAGE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "123", "Test", "test123"})
    void shouldThrowExceptionWhenValueDoesNotMatchPattern(String nonMatchingValue) {
        // when/then
        assertThatThrownBy(() -> Validation.requireValueMatchingPattern(nonMatchingValue, TEST_PATTERN, TEST_MESSAGE))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(TEST_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenValueIsNullForPatternMatching() {
        // when/then
        assertThatThrownBy(() -> Validation.requireValueMatchingPattern(null, TEST_PATTERN, TEST_MESSAGE))
                .isInstanceOf(NullPointerException.class);
    }
}
