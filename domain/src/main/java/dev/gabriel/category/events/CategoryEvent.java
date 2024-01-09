package dev.gabriel.category.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class CategoryEvent extends DomainEvent {
    protected CategoryEvent(UUID categoryId, Long version) {
        super(categoryId, version, Instant.now());
    }
}
