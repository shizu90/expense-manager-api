package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;

public class CategoryDeletedEvent extends CategoryEvent {
    public CategoryDeletedEvent(CategoryId categoryId, Long version) {
        super(categoryId, version);
    }
}
