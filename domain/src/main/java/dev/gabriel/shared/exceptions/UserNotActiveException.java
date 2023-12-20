package dev.gabriel.shared.exceptions;

public class UserNotActiveException extends DomainException {
    public UserNotActiveException(String message) {
        super(message);
    }
}
