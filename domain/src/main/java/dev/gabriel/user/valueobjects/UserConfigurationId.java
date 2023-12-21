package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class UserConfigurationId extends Identity {
    private UserConfigurationId(String id) {
        super(id);
    }

    public static UserConfigurationId create(String id) {
        return new UserConfigurationId(id);
    }
}
