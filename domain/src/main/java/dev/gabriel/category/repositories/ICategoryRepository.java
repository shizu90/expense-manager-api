package dev.gabriel.category.repositories;

import dev.gabriel.category.models.Category;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.Optional;

public interface ICategoryRepository extends IDomainRepository {
    Optional<Category> load(CategoryId categoryId);
    Category registerEvents(Category category);
}
