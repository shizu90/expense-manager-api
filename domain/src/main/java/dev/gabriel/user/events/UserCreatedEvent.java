package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserCreatedEvent extends UserEvent {

    public UserCreatedEvent(UserId userId) {
        super(userId);
    }
}
