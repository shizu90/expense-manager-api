package dev.gabriel.shared.events;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class DomainEvent implements IDomainEvent{
    private final UUID aggregateId;
    private final Long version;
    private final Instant timestamp;

    public DomainEvent(UUID aggregateId, Long version, Instant timestamp) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = timestamp;
    }
}
