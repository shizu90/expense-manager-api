package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;

public class CategoryCreatedEvent extends CategoryEvent {
    public CategoryCreatedEvent(CategoryId categoryId) {
        super(categoryId);
    }
}
