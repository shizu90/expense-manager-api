package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.user.entities.User;
import dev.gabriel.shared.events.DomainEvent;

public class UserUpdatedEvent extends DomainEvent {
    public UserUpdatedEvent(Identity userId) {
        super(userId);
    }
}
