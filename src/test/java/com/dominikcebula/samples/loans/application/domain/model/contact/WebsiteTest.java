package com.dominikcebula.samples.loans.application.domain.model.contact;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WebsiteTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "example.com",
            "www.example.com",
            "http://example.com",
            "https://example.com",
            "https://www.example.com",
            "example.co.uk",
            "example.com/path",
            "example.com:8080",
            "example.com:8080/path"
    })
    void shouldCreateWebsiteWithValidValue(String validWebsite) {
        // when
        Website website = new Website(validWebsite);

        // then
        assertThat(website.getValue()).isEqualTo(validWebsite);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowExceptionWhenValueIsBlank(String blankValue) {
        // when/then
        assertThatThrownBy(() -> new Website(blankValue))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Website value cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // when/then
        assertThatThrownBy(() -> new Website(null))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Website value cannot be empty.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "example",
            "http://",
            "https://",
            "http:/example.com",
            "example@domain.com",
            "ftp://example.com"
    })
    void shouldThrowExceptionWhenValueIsNotValidWebsite(String invalidWebsite) {
        // when/then
        assertThatThrownBy(() -> new Website(invalidWebsite))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Website value is not a valid website.");
    }
}