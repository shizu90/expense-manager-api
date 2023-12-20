package dev.gabriel.shared.entities;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CreatedAt;
import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected Identity identity;
    @Getter protected CreatedAt createdAt;
    @Getter protected static final List<DomainEvent> events = new ArrayList<>();

    protected Entity(Identity id) {
        this.identity = id;
        this.createdAt = CreatedAt.create(Instant.now());
    }
    protected Entity() {}

    protected static void addEvent(DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    protected static void deleteEvent(DomainEvent domainEvent) {
        events.remove(domainEvent);
    }

    public String getIdentity() {
        return (String) identity.getAtomicValues().get(0);
    }
}
