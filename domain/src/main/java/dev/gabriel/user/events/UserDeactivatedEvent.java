package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.valueobjects.UserId;

import java.time.Instant;

public class UserDeactivatedEvent extends DomainEvent {
    public UserDeactivatedEvent(UserId userId) {
        super(userId, Instant.now());
    }
}
