package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BirthDateTest {

    @Test
    void shouldCreateBirthDateWithValidValue() {
        // given
        LocalDate validBirthDate = LocalDate.now().minusYears(30);

        // when
        BirthDate birthDate = new BirthDate(validBirthDate);

        // then
        assertThat(birthDate.getValue()).isEqualTo(validBirthDate);
    }

    @Test
    void shouldCreateBirthDateWithMinimumValidValue() {
        // given
        LocalDate oldestValidBirthDate = LocalDate.now().minusYears(119);

        // when
        BirthDate birthDate = new BirthDate(oldestValidBirthDate);

        // then
        assertThat(birthDate.getValue()).isEqualTo(oldestValidBirthDate);
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new BirthDate(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Birth Date must not be null.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsTooOld() {
        // given
        LocalDate tooOldBirthDate = LocalDate.now().minusYears(121);

        // when/then
        assertThatThrownBy(() -> new BirthDate(tooOldBirthDate))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Birth Date must be within a reasonable period.");
    }
}