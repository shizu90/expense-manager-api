package dev.gabriel.shared.events;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent implements IDomainEvent{
    private final String aggregateId;
    private final Long version;
    private final Instant timestamp;

    public DomainEvent(String aggregateId, Long version, Instant timestamp) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = timestamp;
    }
}
