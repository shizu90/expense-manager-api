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

@Getter
public class Category extends AggregateRoot {
    private CategoryName name;
    private UserId userId;
    private Boolean isDeleted;

    private Category(String id, String name, UserId userId) {
        super(CategoryId.create(id));
        raiseEvent(new CategoryCreatedEvent(
                CategoryId.create(id),
                getNextVersion(),
                CategoryName.create(name),
                userId)
        );
    }

    public static Category create(String id, String name, UserId userId) {
        return new Category(id, name, userId);
    }

    public void rename(String name) {
        raiseEvent(new CategoryRenamedEvent(getId(), getNextVersion(), CategoryName.create(name)));
    }

    public void delete() {
        if(isDeleted) {
            throw new CategoryAlreadyDeletedException(getId().getValue());
        }else {
            raiseEvent(new CategoryDeletedEvent(getId(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(CategoryCreatedEvent event) {
        this.name = event.getName();
        this.userId = event.getUserId();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(CategoryRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(CategoryDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public CategoryId getId() {
        return (CategoryId) id;
    }
}
