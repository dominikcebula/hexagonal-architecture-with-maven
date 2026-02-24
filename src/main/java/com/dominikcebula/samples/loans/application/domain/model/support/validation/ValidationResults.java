package com.dominikcebula.samples.loans.application.domain.model.support.validation;

import java.util.List;
import java.util.Optional;

public class ValidationResults {
    private final List<ValidationResult> validationResults;

    private ValidationResults(ValidationResult... validationResults) {
        this.validationResults = List.of(validationResults);
    }

    public static ValidationResults of(ValidationResult... validationResults) {
        return new ValidationResults(validationResults);
    }

    public boolean isSuccessful() {
        return validationResults.stream()
                .map(ValidationResult::isSuccessful)
                .reduce(Boolean::logicalAnd)
                .orElse(true);
    }

    public List<String> getMessages() {
        return validationResults.stream()
                .map(ValidationResult::getMessage)
                .flatMap(Optional::stream)
                .toList();
    }
}
