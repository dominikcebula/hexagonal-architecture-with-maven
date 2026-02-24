package com.dominikcebula.samples.loans.application.domain.model.support.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validation {
    private Validation() {
    }

    public static <T> void requireNonNull(T obj, String message) {
        if (obj == null)
            throw new DomainValidationException(message);
    }

    public static void requireNonBlank(String value, String message) {
        if (StringUtils.isBlank(value))
            throw new DomainValidationException(message);
    }

    public static void requireValueMatchingCondition(boolean condition, String message) {
        if (!condition)
            throw new DomainValidationException(message);
    }

    public static void requireValueMatchingPattern(String value, Pattern pattern, String message) {
        if (!pattern.matcher(value).matches())
            throw new DomainValidationException(message);
    }
}
