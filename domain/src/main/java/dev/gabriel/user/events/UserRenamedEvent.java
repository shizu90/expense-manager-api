package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;

public class UserRenamedEvent extends UserEvent {
    public UserRenamedEvent(UserId userId) {
        super(userId);
    }
}
