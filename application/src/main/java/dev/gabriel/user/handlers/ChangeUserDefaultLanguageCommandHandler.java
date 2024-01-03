package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.ChangeUserDefaultLanguageCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

public class ChangeUserDefaultLanguageCommandHandler implements ICommandHandler<User, ChangeUserDefaultLanguageCommand> {
    private final IUserRepository userRepository;

    public ChangeUserDefaultLanguageCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(ChangeUserDefaultLanguageCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.changeDefaultUserLanguage(UserLanguage.valueOf(command.getLanguage()));

        return userRepository.save(user);
    }
}
