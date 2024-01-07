package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class UserEvent extends DomainEvent {
    protected UserEvent(String userId, Long version) {
        super(userId, version, Instant.now());
    }
}
