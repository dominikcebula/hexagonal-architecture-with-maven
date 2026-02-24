package com.dominikcebula.samples.loans.application.domain.model.support.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
