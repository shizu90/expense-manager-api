package dev.gabriel.category.events;

import lombok.Getter;

@Getter
public class CategoryRenamedEvent extends CategoryEvent {
    private final String name;

    public CategoryRenamedEvent(String categoryId, Long version, String name) {
        super(categoryId, version);
        this.name = name;
    }
}
