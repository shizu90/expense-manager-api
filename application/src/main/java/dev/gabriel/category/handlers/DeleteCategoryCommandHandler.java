package dev.gabriel.category.handlers;

import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.category.commands.DeleteCategoryCommand;

public class DeleteCategoryCommandHandler implements ICommandHandler<Category, DeleteCategoryCommand> {
    private final ICategoryRepository categoryRepository;

    public DeleteCategoryCommandHandler(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(DeleteCategoryCommand command) {
        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));
        category.delete();

        categoryRepository.save(category);

        return null;
    }
}
