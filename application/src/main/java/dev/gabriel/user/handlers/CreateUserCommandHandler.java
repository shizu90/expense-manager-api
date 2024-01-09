package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.commands.CreateUserCommand;
import dev.gabriel.user.exceptions.UserEmailAlreadyExists;
import dev.gabriel.user.models.User;
import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.services.CheckUniqueUserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUserCommandHandler implements ICommandHandler<User, CreateUserCommand> {
    private final IUserRepository userRepository;
    private final CheckUniqueUserEmailService checkUniqueUserEmailService;

    @Autowired
    public CreateUserCommandHandler(IUserRepository userRepository,
                                    CheckUniqueUserEmailService checkUniqueUserEmailService
    ) {
        this.userRepository = userRepository;
        this.checkUniqueUserEmailService = checkUniqueUserEmailService;
    }

    @Override
    public User handle(CreateUserCommand command) {
        User user = User.create(
                UUID.randomUUID(),
                command.getEmail(),
                command.getName(),
                command.getPassword(),
                CurrencyCode.valueOf(command.getDefaultCurrencyCode()),
                UserLanguage.valueOf(command.getDefaultLanguage())
        );

        if(checkUniqueUserEmailService.getUserFromEmail(user.getEmail()).isPresent())
            throw new UserEmailAlreadyExists(user.getEmail().getValue());

        return userRepository.registerEvents(user);
    }

    @Override
    public Class<CreateUserCommand> getCommandType() {
        return CreateUserCommand.class;
    }
}
