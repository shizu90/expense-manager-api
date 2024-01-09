package dev.gabriel.category.events;

import java.util.UUID;

public class CategoryDeletedEvent extends CategoryEvent {
    public CategoryDeletedEvent(UUID categoryId, Long version) {
        super(categoryId, version);
    }
}
