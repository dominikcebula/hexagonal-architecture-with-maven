package com.dominikcebula.samples.loans.application.domain.model.contact;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final String value;

    public Email(String value) {
        Validation.requireNonBlank(value, "Email cannot be empty.");
        Validation.requireValueMatchingPattern(value, EMAIL_PATTERN, "Email value is not a valid email.");
        this.value = value;
    }
}
