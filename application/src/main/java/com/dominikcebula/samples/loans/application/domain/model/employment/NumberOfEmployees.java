package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class NumberOfEmployees {
    private final int value;

    public NumberOfEmployees(int value) {
        Validation.requireValueMatchingCondition(value > 0, "Number of employees must be greater than zero.");
        Validation.requireValueMatchingCondition(value < 5_000_000, "Number of employees must be within a reasonable range.");
        this.value = value;
    }
}
