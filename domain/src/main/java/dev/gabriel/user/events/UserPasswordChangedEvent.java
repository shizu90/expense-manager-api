package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserPasswordChangedEvent extends UserEvent {
    public UserPasswordChangedEvent(UserId userId) {
        super(userId);
    }
}
