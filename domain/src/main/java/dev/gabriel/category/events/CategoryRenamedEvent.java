package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;

public class CategoryRenamedEvent extends CategoryEvent {
    public CategoryRenamedEvent(CategoryId categoryId) {
        super(categoryId);
    }
}
