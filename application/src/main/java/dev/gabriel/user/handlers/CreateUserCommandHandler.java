package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.commands.CreateUserCommand;
import dev.gabriel.user.exceptions.UserEmailAlreadyExists;
import dev.gabriel.user.models.User;
import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.repositories.IUserRepository;

import java.util.UUID;

public class CreateUserCommandHandler implements ICommandHandler<User, CreateUserCommand> {
    private final IUserRepository userRepository;

    public CreateUserCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(CreateUserCommand command) {
        User user = User.create(
                UUID.randomUUID().toString(),
                command.getEmail(),
                command.getName(),
                command.getPassword(),
                CurrencyCode.valueOf(command.getDefaultCurrencyCode()),
                UserLanguage.valueOf(command.getDefaultLanguage())
        );

        if(userRepository.existsByEmail(user.getEmail())) throw new UserEmailAlreadyExists(user.getEmail().getValue());

        return userRepository.save(user);
    }
}
