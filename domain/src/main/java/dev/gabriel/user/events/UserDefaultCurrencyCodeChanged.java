package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserDefaultCurrencyCodeChanged extends UserEvent {
    public UserDefaultCurrencyCodeChanged(UserId userId) {
        super(userId);
    }
}
