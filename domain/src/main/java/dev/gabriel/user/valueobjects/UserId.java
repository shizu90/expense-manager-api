package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class UserId extends Identity {
    private UserId(UUID value) {
        super(value);
    }

    public static UserId create(UUID value) {
        return new UserId(value);
    }
}
