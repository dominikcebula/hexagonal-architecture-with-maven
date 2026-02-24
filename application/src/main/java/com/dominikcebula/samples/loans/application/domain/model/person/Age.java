package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Age {
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;

    private final int value;

    public Age(long value) {
        Validation.requireValueMatchingCondition(value > MIN_AGE, "Age cannot be negative.");
        Validation.requireValueMatchingCondition(value < MAX_AGE, "Age cannot be greater than %d.".formatted(MAX_AGE));

        this.value = (int) value;
    }

    public boolean isAdult() {
        return value >= 18;
    }
}
