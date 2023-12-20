package dev.gabriel.exceptions;

public class InsufficientFundsException extends DomainException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
