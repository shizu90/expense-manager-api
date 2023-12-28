package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class CategoryImageChangedEvent extends DomainEvent {
    public CategoryImageChangedEvent(CategoryId categoryId) {
        super(categoryId, Instant.now());
    }
}
