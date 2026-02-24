package com.dominikcebula.samples.loans.application.domain.model.identifier;

import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@EqualsAndHashCode
@ToString
public class Identifier {
    private Long value;

    public Identifier(Long value) {
        Validation.requireNonNull(value, "Identifier must not be null");
        Validation.requireValueMatchingCondition(value > 0, "Identifier cannot be negative.");
        this.value = value;
    }

    Identifier() {
    }

    public static Identifier empty() {
        return new Identifier();
    }

    public Optional<Long> getValue() {
        return Optional.ofNullable(value);
    }
}
