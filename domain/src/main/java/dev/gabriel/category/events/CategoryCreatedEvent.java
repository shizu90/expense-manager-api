package dev.gabriel.category.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private final String name;
    private final UUID userId;

    public CategoryCreatedEvent(UUID categoryId, Long version, String name, UUID userId) {
        super(categoryId, version);
        this.name = name;
        this.userId = userId;
    }
}
