package dev.gabriel.exceptions;

public class UserNotActiveException extends RuntimeException {
    public UserNotActiveException(String message) {
        super(message);
    }
}
