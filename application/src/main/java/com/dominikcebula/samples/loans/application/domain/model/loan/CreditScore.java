package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class CreditScore {
    private final int value;

    public CreditScore(int value) {
        Validation.requireValueMatchingCondition(value >= 0, "Credit score must be greater than or equal to zero.");
        Validation.requireValueMatchingCondition(value <= 1000, "Credit score must be less than or equal to 1000.");
        this.value = value;
    }
}
