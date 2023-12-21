package dev.gabriel.shared.events;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.DomainEventId;
import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class DomainEvent<TAggregate extends AggregateRoot> implements IDomainEvent {
    protected DomainEventId id;
    protected TAggregate payload;
    protected Instant createdAt;

    protected DomainEvent(TAggregate payload) {
        this.id = DomainEventId.create(UUID.randomUUID().toString());
        this.payload = payload;
        this.createdAt = Instant.now();
    }
}
