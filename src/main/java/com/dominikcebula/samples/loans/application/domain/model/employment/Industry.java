package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Industry {
    private final String value;

    public Industry(String value) {
        Validation.requireNonBlank(value, "Industry name cannot be empty.");
        this.value = value;
    }
}
