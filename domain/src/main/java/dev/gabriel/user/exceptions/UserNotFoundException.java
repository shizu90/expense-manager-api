package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String id) {
        super("User " + id + " was not found.");
    }
}
