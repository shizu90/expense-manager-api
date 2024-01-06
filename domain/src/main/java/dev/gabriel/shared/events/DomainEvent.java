package dev.gabriel.shared.events;

import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent implements IDomainEvent{
    private final Identity aggregateId;
    private final Long version;
    private final Instant timestamp;

    public DomainEvent(Identity aggregateId, Long version, Instant timestamp) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = timestamp;
    }
}
