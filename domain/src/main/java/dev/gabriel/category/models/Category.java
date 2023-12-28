package dev.gabriel.category.models;

import dev.gabriel.category.events.CategoryCreatedEvent;
import dev.gabriel.category.events.CategoryDeletedEvent;
import dev.gabriel.category.events.CategoryImageChangedEvent;
import dev.gabriel.category.events.CategoryRenamedEvent;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.category.valueobjects.CategoryImage;
import dev.gabriel.category.valueobjects.CategoryName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Category extends AggregateRoot {
    private CategoryName name;
    private CategoryImage image;
    private UserId userId;

    private Category(String id, String name, String image, UserId userId) {
        super(CategoryId.create(id));
        this.name = CategoryName.create(name);
        this.image = CategoryImage.create(image);
        this.userId = userId;
    }

    public static Category create(String id, String name, String image, UserId userId) {
        Category category = new Category(id, name, image, userId);
        category.raiseEvent(new CategoryCreatedEvent(category.getId()));
        return category;
    }

    public void rename(String name) {
        this.name = CategoryName.create(name);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new CategoryRenamedEvent(getId()));
    }

    public void changeImage(String image) {
        this.image = CategoryImage.create(image);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new CategoryImageChangedEvent(getId()));
    }

    public void delete() {
        raiseEvent(new CategoryDeletedEvent(getId()));
    }

    @Override
    public CategoryId getId() {
        return (CategoryId) id;
    }
}
