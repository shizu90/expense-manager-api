package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserDeactivatedEvent extends UserEvent {
    public UserDeactivatedEvent(UserId userId) {
        super(userId);
    }
}
