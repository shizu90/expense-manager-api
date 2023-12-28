package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class UserId extends Identity {
    private UserId(String value) {
        super(value);
    }

    public static UserId create(String value) {
        return new UserId(value);
    }
}
