package dev.gabriel.user.events;

import java.util.UUID;

public class UserDeletedEvent extends UserEvent {

    public UserDeletedEvent(UUID userId, Long version) {
        super(userId, version);
    }
}
