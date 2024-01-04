package dev.gabriel.transaction.handlers;

import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.transaction.commands.RenameCategoryCommand;

public class RenameCategoryCommandHandler implements ICommandHandler<Category, RenameCategoryCommand> {
    private final ICategoryRepository categoryRepository;

    public RenameCategoryCommandHandler(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(RenameCategoryCommand command) {
        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));
        category.rename(command.getName());

        return categoryRepository.save(category);
    }
}
