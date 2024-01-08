package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.StopReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class StopReminderCommandHandler implements ICommandHandler<Reminder, StopReminderCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public StopReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(StopReminderCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.stop();

        return reminderRepository.save(reminder);
    }
}
