package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserDefaultLanguageChanged extends UserEvent {
    public UserDefaultLanguageChanged(UserId userId) {
        super(userId);
    }
}
