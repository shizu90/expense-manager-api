package dev.gabriel.user.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class UserAlreadyDeletedException extends AlreadyDeletedException {
    public UserAlreadyDeletedException() {
        super("User is already deleted.");
    }
}
