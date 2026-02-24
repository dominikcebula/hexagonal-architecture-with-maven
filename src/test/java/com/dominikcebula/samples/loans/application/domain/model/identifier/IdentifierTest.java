package com.dominikcebula.samples.loans.application.domain.model.identifier;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IdentifierTest {

    @Test
    void shouldCreateIdentifierWithValidValue() {
        // given
        Long validValue = 123L;

        // when
        Identifier identifier = new Identifier(validValue);

        // then
        assertThat(identifier.getValue()).isEqualTo(Optional.of(validValue));
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new Identifier(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Identifier must not be null");
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void shouldThrowExceptionWhenValueIsNotPositive(long invalidValue) {
        // when/then
        assertThatThrownBy(() -> new Identifier(invalidValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Identifier cannot be negative.");
    }

    @Test
    void shouldCreateEmptyIdentifier() {
        // when
        Identifier identifier = Identifier.empty();

        // then
        assertThat(identifier.getValue()).isEmpty();
    }
}