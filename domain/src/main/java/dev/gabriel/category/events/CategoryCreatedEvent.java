package dev.gabriel.category.events;

import lombok.Getter;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private final String name;
    private final String userId;

    public CategoryCreatedEvent(String categoryId, Long version, String name, String userId) {
        super(categoryId, version);
        this.name = name;
        this.userId = userId;
    }
}
