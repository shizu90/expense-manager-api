package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserEmailChangedEvent extends UserEvent {
    public UserEmailChangedEvent(UserId userId) {
        super(userId);
    }
}
