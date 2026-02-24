package com.dominikcebula.samples.loans.application.domain.model.contact;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class PhoneNumber {
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\+?[0-9]{1,4}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}(\\s?(ext|x|ext.)\\s?[0-9]{1,5})?$");

    private final String value;

    public PhoneNumber(String value) {
        Validation.requireNonBlank(value, "Phone number cannot be empty.");
        Validation.requireValueMatchingPattern(value, PHONE_NUMBER_PATTERN, "Phone number value is not a valid phone number.");
        this.value = value;
    }
}
