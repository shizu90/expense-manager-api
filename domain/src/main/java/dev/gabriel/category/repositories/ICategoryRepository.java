package dev.gabriel.category.repositories;

import dev.gabriel.category.models.Category;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends IDomainRepository {
    Optional<Category> findById(String id);
    List<Category> findByUserId(String userId);
    Category save(Category category);
    void deleteById(String id);
}
