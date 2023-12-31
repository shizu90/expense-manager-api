package dev.gabriel.shared.exceptions;

public class AlreadyDeletedException extends DomainException {
    public AlreadyDeletedException(String message) {
        super(message);
    }
}
