package dev.gabriel.category.eventstore;

import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;

import java.util.Optional;

public class CategoryEventStore implements ICategoryRepository {
    @Override
    public Optional<Category> load(CategoryId categoryId) {
        return Optional.empty();
    }

    @Override
    public Category registerEvents(Category category) {
        return null;
    }
}
