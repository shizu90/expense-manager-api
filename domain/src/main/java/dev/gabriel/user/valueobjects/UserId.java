package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class UserId extends Identity {
    private UserId(String id) {
        super(id);
    }

    public static UserId create(String id) {
        return new UserId(id);
    }
}
