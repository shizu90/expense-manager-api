package dev.gabriel.category.handlers;

import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.category.commands.CreateCategoryCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCategoryCommandHandler implements ICommandHandler<Category, CreateCategoryCommand> {
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CreateCategoryCommandHandler(ICategoryRepository categoryRepository, IUserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Category handle(CreateCategoryCommand command) {
        User user = userRepository
                .load(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        Category category = Category.create(
                UUID.randomUUID(),
                command.getName(),
                user.getId()
        );

        return categoryRepository.registerEvents(category);
    }

    @Override
    public Class<CreateCategoryCommand> getCommandType() {
        return CreateCategoryCommand.class;
    }
}
