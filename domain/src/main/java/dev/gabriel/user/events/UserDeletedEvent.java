package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserDeletedEvent extends UserEvent {

    public UserDeletedEvent(UserId userId, Long version) {
        super(userId, version);
    }
}
