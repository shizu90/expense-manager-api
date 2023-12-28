package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class CategoryDeletedEvent extends DomainEvent {
    public CategoryDeletedEvent(CategoryId categoryId) {
        super(categoryId, Instant.now());
    }
}
