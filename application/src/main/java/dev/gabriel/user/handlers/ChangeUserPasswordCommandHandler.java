package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.ChangeUserPasswordCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

public class ChangeUserPasswordCommandHandler implements ICommandHandler<User, ChangeUserPasswordCommand> {
    private final IUserRepository userRepository;

    public ChangeUserPasswordCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(ChangeUserPasswordCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.changePassword(command.getPassword());

        return userRepository.save(user);
    }
}
