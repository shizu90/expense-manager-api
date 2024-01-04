package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.RenameReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RenameReminderCommandHandler implements ICommandHandler<Reminder, RenameReminderCommand> {
    private final IReminderRepository reminderRepository;

    public RenameReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(RenameReminderCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.rename(command.getName());

        return reminderRepository.save(reminder);
    }
}
