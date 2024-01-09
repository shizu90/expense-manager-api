package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(UUID id) {
        super("User " + id.toString() + " was not found.");
    }
}
