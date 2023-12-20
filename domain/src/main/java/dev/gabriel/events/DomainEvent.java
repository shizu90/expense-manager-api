package dev.gabriel.events;

import dev.gabriel.primitives.Entity;

import java.time.Instant;

public abstract class DomainEvent<T extends Entity> {
    protected String id;
    protected T payload;
    protected Instant createdAt;
}
