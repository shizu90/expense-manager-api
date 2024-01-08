package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.RenameUserCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;

public class RenameUserCommandHandler implements ICommandHandler<User, RenameUserCommand> {
    private final IUserRepository userRepository;

    @Autowired
    public RenameUserCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(RenameUserCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.rename(command.getName());

        return userRepository.save(user);
    }
}
