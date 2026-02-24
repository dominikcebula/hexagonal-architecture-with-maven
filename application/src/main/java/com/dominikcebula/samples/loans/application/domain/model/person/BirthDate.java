package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
public class BirthDate {
    private final LocalDate value;

    public BirthDate(LocalDate value) {
        Validation.requireNonNull(value, "Birth Date must not be null.");
        LocalDate minBirthDate = LocalDate.now().minusYears(120);
        Validation.requireValueMatchingCondition(value.isAfter(minBirthDate), "Birth Date must be within a reasonable period.");

        this.value = value;
    }
}
