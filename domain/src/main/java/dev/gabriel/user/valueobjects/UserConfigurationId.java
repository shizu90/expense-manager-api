package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class UserConfigurationId extends Identity {
    private UserConfigurationId(UUID value) {
        super(value);
    }

    public static UserConfigurationId create(UUID value) {
        return new UserConfigurationId(value);
    }
}
