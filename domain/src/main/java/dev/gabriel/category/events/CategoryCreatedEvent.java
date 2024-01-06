package dev.gabriel.category.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.category.valueobjects.CategoryName;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private final CategoryName name;
    private final UserId userId;

    public CategoryCreatedEvent(CategoryId categoryId, Long version, CategoryName name, UserId userId) {
        super(categoryId, version);
        this.name = name;
        this.userId = userId;
    }
}
