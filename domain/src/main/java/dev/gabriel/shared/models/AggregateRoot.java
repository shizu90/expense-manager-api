package dev.gabriel.shared.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AggregateRoot extends Entity {
    protected Instant createdAt;
    protected Instant updatedAt;
    protected List<DomainEvent> events;
    protected Boolean isDeleted;

    protected AggregateRoot(Identity id) {
        super(id);
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.events = new ArrayList<>();
        this.isDeleted = false;
    }

    protected void raiseEvent(DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    protected void removeEvent(DomainEvent domainEvent) {
        events.remove(domainEvent);
    }

    protected void clearEvents() {
        events.clear();
    }
}
