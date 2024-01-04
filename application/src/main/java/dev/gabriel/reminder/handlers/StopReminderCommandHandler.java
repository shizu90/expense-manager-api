package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.StopReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class StopReminderCommandHandler implements ICommandHandler<Reminder, StopReminderCommand> {
    private final IReminderRepository reminderRepository;

    public StopReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(StopReminderCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.stop();

        return reminderRepository.save(reminder);
    }
}
