package com.dominikcebula.samples.loans.application.domain.model.person;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class FirstName {
    private final String value;

    public FirstName(String value) {
        Validation.requireNonBlank(value, "First Name cannot be blank.");
        this.value = value;
    }
}
