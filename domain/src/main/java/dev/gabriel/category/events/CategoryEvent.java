package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class CategoryEvent extends DomainEvent {
    protected CategoryEvent(CategoryId categoryId, Long version) {
        super(categoryId, version, Instant.now());
    }
}
