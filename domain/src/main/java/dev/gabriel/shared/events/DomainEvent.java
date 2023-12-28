package dev.gabriel.shared.events;

import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent implements IDomainEvent{
    private final Identity id;
    private final Instant raisedAt;

    public DomainEvent(Identity id, Instant raisedAt) {
        this.id = id;
        this.raisedAt = raisedAt;
    }
}
