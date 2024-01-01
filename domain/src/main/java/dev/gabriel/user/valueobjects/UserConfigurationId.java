package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class UserConfigurationId extends Identity {
    private UserConfigurationId(String value) {
        super(value);
    }

    public static UserConfigurationId create(String value) {
        return new UserConfigurationId(value);
    }
}
