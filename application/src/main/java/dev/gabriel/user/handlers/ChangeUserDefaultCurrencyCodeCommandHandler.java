package dev.gabriel.user.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.commands.ChangeUserDefaultCurrencyCodeCommand;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserDefaultCurrencyCodeCommandHandler implements ICommandHandler<User, ChangeUserDefaultCurrencyCodeCommand> {
    private final IUserRepository userRepository;

    @Autowired
    public ChangeUserDefaultCurrencyCodeCommandHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(ChangeUserDefaultCurrencyCodeCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        user.changeDefaultCurrencyCode(CurrencyCode.valueOf(command.getCurrencyCode()));

        return userRepository.save(user);
    }

    @Override
    public Class<ChangeUserDefaultCurrencyCodeCommand> getCommandType() {
        return ChangeUserDefaultCurrencyCodeCommand.class;
    }
}
