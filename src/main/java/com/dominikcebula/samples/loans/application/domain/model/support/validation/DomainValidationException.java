package com.dominikcebula.samples.loans.application.domain.model.support.validation;

import com.dominikcebula.samples.loans.application.domain.model.support.exception.DomainException;

public class DomainValidationException extends DomainException {
    public DomainValidationException(String message) {
        super(message);
    }
}
