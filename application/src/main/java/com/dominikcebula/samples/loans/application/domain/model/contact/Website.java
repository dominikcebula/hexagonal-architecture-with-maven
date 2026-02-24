package com.dominikcebula.samples.loans.application.domain.model.contact;


import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class Website {
    private static final Pattern WEBSITE_PATTERN = Pattern.compile("^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(:[0-9]{1,5})?(/.*)?$");

    private final String value;

    public Website(String value) {
        Validation.requireNonBlank(value, "Website value cannot be empty.");
        Validation.requireValueMatchingPattern(value, WEBSITE_PATTERN, "Website value is not a valid website.");
        this.value = value;
    }
}
