package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class UserEmailAlreadyExists extends DomainException {
    public UserEmailAlreadyExists(String email) {
        super("User with email " + email + " already exists.");
    }
}
