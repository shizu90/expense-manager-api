package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class UserAlreadyActivatedException extends DomainException {
    public UserAlreadyActivatedException() {
        super("User is already activated.");
    }
}
