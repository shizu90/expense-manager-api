package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;

public class CategoryDeletedEvent extends CategoryEvent {
    public CategoryDeletedEvent(CategoryId categoryId) {
        super(categoryId);
    }
}
