package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class UserValidationException extends DataValidationException {
    public UserValidationException(String field, String message) {
        super("User validation failed on " + field + ": " + message);
    }
}
