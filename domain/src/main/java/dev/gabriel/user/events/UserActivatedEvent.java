package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserActivatedEvent extends UserEvent {
    public UserActivatedEvent(UserId userId) {
        super(userId);
    }
}
