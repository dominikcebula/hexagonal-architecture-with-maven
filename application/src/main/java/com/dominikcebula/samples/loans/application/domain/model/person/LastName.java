package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class LastName {
    private final String value;

    public LastName(String value) {
        Validation.requireNonBlank(value, "Last Name cannot be empty.");
        this.value = value;
    }
}
