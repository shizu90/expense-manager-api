package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class CategoryEvent extends DomainEvent {
    protected CategoryEvent(CategoryId categoryId) {
        super(categoryId, Instant.now());
    }
}
