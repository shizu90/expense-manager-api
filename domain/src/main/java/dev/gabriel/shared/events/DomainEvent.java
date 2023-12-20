package dev.gabriel.shared.events;

import dev.gabriel.shared.valueobjects.Identity;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    protected Identity id;
    protected Identity entityId;
    protected Instant createdAt;

    protected DomainEvent(Identity entityId) {
        this.id = Identity.create(UUID.randomUUID().toString());
        this.entityId = entityId;
        this.createdAt = Instant.now();
    }
}
