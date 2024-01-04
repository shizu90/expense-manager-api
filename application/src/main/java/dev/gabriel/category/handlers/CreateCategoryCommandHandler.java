package dev.gabriel.category.handlers;

import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.category.commands.CreateCategoryCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.UUID;

public class CreateCategoryCommandHandler implements ICommandHandler<Category, CreateCategoryCommand> {
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;

    public CreateCategoryCommandHandler(ICategoryRepository categoryRepository, IUserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Category execute(CreateCategoryCommand command) {
        User user = userRepository
                .findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        Category category = Category.create(
                UUID.randomUUID().toString(),
                command.getName(),
                user.getId()
        );

        return categoryRepository.save(category);
    }
}
