package dev.gabriel.shared.exceptions;

import lombok.Getter;

@Getter
public abstract class DataValidationException extends DomainException {
    public DataValidationException(String message) {
        super(message);
    }
}
