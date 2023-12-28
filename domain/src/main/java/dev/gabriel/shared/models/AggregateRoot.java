package dev.gabriel.shared.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CreatedAt;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AggregateRoot extends Entity {
    protected CreatedAt createdAt;
    protected UpdatedAt updatedAt;
    protected List<DomainEvent> events;

    protected AggregateRoot(Identity id) {
        super(id);
        this.createdAt = CreatedAt.create(Instant.now());
        this.updatedAt = UpdatedAt.create(Instant.now());
        this.events = new ArrayList<>();
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
