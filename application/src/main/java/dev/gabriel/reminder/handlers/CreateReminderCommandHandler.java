package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.CreateReminderCommand;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.UUID;

public class CreateReminderCommandHandler implements ICommandHandler<Reminder, CreateReminderCommand> {
    private final IReminderRepository reminderRepository;
    private final IUserRepository userRepository;

    public CreateReminderCommandHandler(IReminderRepository reminderRepository, IUserRepository userRepository) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reminder execute(CreateReminderCommand command) {
        User user = userRepository
                .findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        Reminder reminder = Reminder.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getDescription(),
                command.getRecurrence(),
                command.getMaxRuns(),
                UserId.create(command.getUserId())
        );

        reminder.start();

        return reminderRepository.save(reminder);
    }
}
