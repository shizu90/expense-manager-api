package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.RestartReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RestartReminderCommandHandler implements ICommandHandler<Reminder, RestartReminderCommand> {
    private final IReminderRepository reminderRepository;

    public RestartReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(RestartReminderCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.restart();

        return reminderRepository.save(reminder);
    }
}
