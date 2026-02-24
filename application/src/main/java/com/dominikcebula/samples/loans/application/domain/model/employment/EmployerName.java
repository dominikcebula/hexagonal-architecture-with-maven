package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class EmployerName {
    private final String value;

    public EmployerName(String value) {
        Validation.requireNonBlank(value, "Employer name cannot be empty.");
        this.value = value;
    }
}
