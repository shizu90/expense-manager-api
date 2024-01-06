package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.valueobjects.UserId;

import java.time.Instant;

public class UserEvent extends DomainEvent {
    protected UserEvent(UserId userId, Long version) {
        super(userId, version, Instant.now());
    }
}
