package dev.gabriel.shared.events;

import dev.gabriel.shared.entities.Entity;

import java.time.Instant;

public abstract class DomainEvent<T extends Entity> {
    protected String id;
    protected T payload;
    protected Instant createdAt;
}
