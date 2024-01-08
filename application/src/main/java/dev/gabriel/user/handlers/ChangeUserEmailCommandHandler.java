package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.ChangeUserEmailCommand;
import dev.gabriel.user.exceptions.UserEmailAlreadyExists;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangeUserEmailCommandHandler implements ICommandHandler<User, ChangeUserEmailCommand> {
    private final IUserRepository userRepository;

    @Autowired
    public ChangeUserEmailCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(ChangeUserEmailCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.changeEmail(command.getEmail());

        if(userRepository.existsByEmail(user.getEmail())) throw new UserEmailAlreadyExists(user.getEmail().getValue());

        return userRepository.save(user);
    }
}
