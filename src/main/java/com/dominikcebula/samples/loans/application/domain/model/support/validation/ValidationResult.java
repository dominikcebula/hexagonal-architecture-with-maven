package com.dominikcebula.samples.loans.application.domain.model.support.validation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

@EqualsAndHashCode
@ToString
public class ValidationResult {
    private final boolean result;
    private String message;

    private ValidationResult(boolean result) {
        this.result = result;
    }

    private ValidationResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public static ValidationResult of(ValidationRunnable validationRunnable) {
        try {
            validationRunnable.run();
            return ValidationResult.success();
        } catch (DomainValidationException e) {
            return ValidationResult.failure(e.getMessage());
        }
    }

    public static ValidationResult success() {
        return new ValidationResult(true);
    }

    public static ValidationResult failure(String message) {
        return new ValidationResult(false, message);
    }

    public boolean isSuccessful() {
        return result;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    @FunctionalInterface
    public interface ValidationRunnable {
        void run() throws DomainValidationException;
    }
}
