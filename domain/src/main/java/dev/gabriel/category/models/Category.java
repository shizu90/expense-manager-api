package dev.gabriel.category.models;

import dev.gabriel.category.events.CategoryCreatedEvent;
import dev.gabriel.category.events.CategoryDeletedEvent;
import dev.gabriel.category.events.CategoryRenamedEvent;
import dev.gabriel.category.exceptions.CategoryAlreadyDeletedException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.category.valueobjects.CategoryName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Category extends AggregateRoot {
    private CategoryName name;
    private UserId userId;

    private Category(String id, String name, UserId userId) {
        super(CategoryId.create(id));
        this.name = CategoryName.create(name);
        this.userId = userId;
    }

    public static Category create(String id, String name, UserId userId) {
        Category category = new Category(id, name, userId);
        category.raiseEvent(new CategoryCreatedEvent(category.getId()));
        return category;
    }

    public void rename(String name) {
        this.name = CategoryName.create(name);
        updatedAt = Instant.now();
        raiseEvent(new CategoryRenamedEvent(getId()));
    }

    public void delete() {
        if(isDeleted) {
            throw new CategoryAlreadyDeletedException(getId().getValue());
        }else {
            isDeleted = true;
            raiseEvent(new CategoryDeletedEvent(getId()));
        }
    }

    @Override
    public CategoryId getId() {
        return (CategoryId) id;
    }
}
