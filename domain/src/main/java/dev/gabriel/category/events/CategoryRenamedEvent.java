package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.category.valueobjects.CategoryName;
import lombok.Getter;

@Getter
public class CategoryRenamedEvent extends CategoryEvent {
    private final CategoryName name;

    public CategoryRenamedEvent(CategoryId categoryId, Long version, CategoryName name) {
        super(categoryId, version);
        this.name = name;
    }
}
