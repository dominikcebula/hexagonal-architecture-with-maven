package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Period;

@Getter
@EqualsAndHashCode
@ToString
public class TermsInMonths {
    private static final long MIN_TERMS_IN_MONTH = 1;
    private static final long MAX_TERMS_IN_MONTH = Period.ofYears(30).toTotalMonths();

    private final int value;

    public TermsInMonths(Period period) {
        this(period.toTotalMonths());
    }

    public TermsInMonths(long value) {
        Validation.requireValueMatchingCondition(value >= MIN_TERMS_IN_MONTH, "Loan must be given for at least one month.");
        Validation.requireValueMatchingCondition(value <= MAX_TERMS_IN_MONTH, String.format("Loan must be given for a maximum period of %d months.", MAX_TERMS_IN_MONTH));
        this.value = (int) value;
    }
}
