package dev.gabriel.category.handlers;

import dev.gabriel.category.commands.RenameCategoryCommand;
import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameCategoryCommandHandler implements ICommandHandler<Category, RenameCategoryCommand> {
    private final ICategoryRepository categoryRepository;

    @Autowired
    public RenameCategoryCommandHandler(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category handle(RenameCategoryCommand command) {
        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));
        category.rename(command.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Class<RenameCategoryCommand> getCommandType() {
        return RenameCategoryCommand.class;
    }
}
