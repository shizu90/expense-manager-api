package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class UserAlreadyDeactivatedException extends DomainException {
    public UserAlreadyDeactivatedException() {
        super("User is already deactivated.");
    }
}
