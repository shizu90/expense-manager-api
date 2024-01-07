package dev.gabriel.category.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class CategoryEvent extends DomainEvent {
    protected CategoryEvent(String categoryId, Long version) {
        super(categoryId, version, Instant.now());
    }
}
