package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.ChangeUserDefaultLanguageCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangeUserDefaultLanguageCommandHandler implements ICommandHandler<User, ChangeUserDefaultLanguageCommand> {
    private final IUserRepository userRepository;

    @Autowired
    public ChangeUserDefaultLanguageCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(ChangeUserDefaultLanguageCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.changeDefaultUserLanguage(UserLanguage.valueOf(command.getLanguage()));

        return userRepository.save(user);
    }
}
