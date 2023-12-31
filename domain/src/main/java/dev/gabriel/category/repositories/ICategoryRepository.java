package dev.gabriel.category.repositories;

import dev.gabriel.category.models.Category;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends IDomainRepository {
    Optional<Category> findById(CategoryId categoryId);
    List<Category> findByUserId(UserId userId);
    Category save(Category category);
    void deleteById(CategoryId categoryId);
}
