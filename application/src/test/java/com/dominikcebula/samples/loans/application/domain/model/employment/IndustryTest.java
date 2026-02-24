package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IndustryTest {

    @Test
    void shouldCreateIndustryWithValidValue() {
        // given
        String validIndustry = "Technology";

        // when
        Industry industry = new Industry(validIndustry);

        // then
        assertThat(industry.getValue()).isEqualTo(validIndustry);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new Industry(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Industry name cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new Industry(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Industry name cannot be empty.");
    }
}
