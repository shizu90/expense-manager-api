package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.ChangeUserEmailCommand;
import dev.gabriel.user.exceptions.UserEmailAlreadyExists;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.services.CheckUniqueUserEmailService;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserEmailCommandHandler implements ICommandHandler<User, ChangeUserEmailCommand> {
    private final IUserRepository userRepository;
    private final CheckUniqueUserEmailService checkUniqueUserEmailService;

    @Autowired
    public ChangeUserEmailCommandHandler(IUserRepository userRepository,
                                         CheckUniqueUserEmailService checkUniqueUserEmailService
    ) {
        this.userRepository = userRepository;
        this.checkUniqueUserEmailService = checkUniqueUserEmailService;
    }

    @Override
    public User handle(ChangeUserEmailCommand command) {
        User user = userRepository
                .load(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        Email.validate(command.getEmail());
        if(checkUniqueUserEmailService.getUserFromEmail(Email.create(command.getEmail())).isPresent()) {
            throw new UserEmailAlreadyExists(user.getEmail().getValue());
        }

        user.changeEmail(command.getEmail());

        return userRepository.registerEvents(user);
    }

    @Override
    public Class<ChangeUserEmailCommand> getCommandType() {
        return ChangeUserEmailCommand.class;
    }
}
