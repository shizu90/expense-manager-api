package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class UserEvent extends DomainEvent {
    protected UserEvent(UUID userId, Long version) {
        super(userId, version, Instant.now());
    }
}
