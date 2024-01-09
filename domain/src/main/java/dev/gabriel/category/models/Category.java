package dev.gabriel.category.models;

import dev.gabriel.category.events.CategoryCreatedEvent;
import dev.gabriel.category.events.CategoryDeletedEvent;
import dev.gabriel.category.events.CategoryRenamedEvent;
import dev.gabriel.category.exceptions.CategoryAlreadyDeletedException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.category.valueobjects.CategoryName;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Category extends AggregateRoot {
    private CategoryName name;
    private UserId userId;
    private Boolean isDeleted;

    private Category(UUID id, String name, UserId userId) {
        super(CategoryId.create(id));
        CategoryName.validate(name);
        raiseEvent(new CategoryCreatedEvent(
                id,
                getNextVersion(),
                name,
                userId.getValue())
        );
    }

    private Category(UUID id, List<DomainEvent> events) {
        super(CategoryId.create(id), events);
    }

    public static Category create(UUID id, String name, UserId userId) {
        return new Category(id, name, userId);
    }

    public static Category create(UUID id, List<DomainEvent> events) {
        return new Category(id, events);
    }

    public void rename(String name) {
        CategoryName.validate(name);
        raiseEvent(new CategoryRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void delete() {
        if(isDeleted) {
            throw new CategoryAlreadyDeletedException();
        }else {
            raiseEvent(new CategoryDeletedEvent(getId().getValue(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(CategoryCreatedEvent event) {
        this.name = CategoryName.create(event.getName());
        this.userId = UserId.create(event.getUserId());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(CategoryRenamedEvent event) {
        this.name = CategoryName.create(event.getName());
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
