package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.commands.DeleteUserCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteUserCommandHandler implements ICommandHandler<User, DeleteUserCommand> {
    private final IUserRepository userRepository;

    @Autowired
    public DeleteUserCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(DeleteUserCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.delete();

        userRepository.save(user);

        return null;
    }
}
