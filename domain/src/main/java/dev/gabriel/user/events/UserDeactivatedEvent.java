package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.events.DomainEvent;

public class UserDeactivatedEvent extends DomainEvent {
    public UserDeactivatedEvent(Identity userId) {
        super(userId);
    }
}
