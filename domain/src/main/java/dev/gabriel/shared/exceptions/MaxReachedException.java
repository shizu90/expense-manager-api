package dev.gabriel.shared.exceptions;

public class MaxReachedException extends DomainException {
    public MaxReachedException(String message) {
        super(message);
    }
}
