package dev.gabriel.exceptions;

public class DifferentCurrencyTypeException extends DomainException {
    public DifferentCurrencyTypeException(String message) {
        super(message);
    }
}
