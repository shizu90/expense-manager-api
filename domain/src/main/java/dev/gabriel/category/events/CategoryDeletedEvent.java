package dev.gabriel.category.events;

public class CategoryDeletedEvent extends CategoryEvent {
    public CategoryDeletedEvent(String categoryId, Long version) {
        super(categoryId, version);
    }
}
