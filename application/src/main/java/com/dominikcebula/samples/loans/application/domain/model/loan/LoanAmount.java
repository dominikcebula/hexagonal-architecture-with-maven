package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.javamoney.moneta.Money;


@Getter
@EqualsAndHashCode
@ToString
public class LoanAmount {
    private final Money value;

    public LoanAmount(Money value) {
        Validation.requireNonNull(value, "Loan amount cannot be null.");
        Validation.requireValueMatchingCondition(value.isPositive(), "Loan amount must be positive.");
        this.value = value;
    }
}
