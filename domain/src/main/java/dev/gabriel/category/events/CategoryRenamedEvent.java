package dev.gabriel.category.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryRenamedEvent extends CategoryEvent {
    private final String name;

    public CategoryRenamedEvent(UUID categoryId, Long version, String name) {
        super(categoryId, version);
        this.name = name;
    }
}
