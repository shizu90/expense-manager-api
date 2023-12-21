package dev.gabriel.shared.entities;

import dev.gabriel.shared.events.IDomainEvent;
import dev.gabriel.shared.valueobjects.CreatedAt;
import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AggregateRoot extends Entity {
    protected CreatedAt createdAt;
    protected final List<IDomainEvent> events;
    protected AggregateRoot(Identity id) {
        super(id);
        this.events = new ArrayList<>();
        this.createdAt = CreatedAt.create(Instant.now());
    }

    protected void addEvent(IDomainEvent domainEvent) {
        events.add(domainEvent);
    }

    protected void removeEvent(IDomainEvent domainEvent) {
        events.remove(domainEvent);
    }

    protected void clear() {
        events.clear();
    }
}
