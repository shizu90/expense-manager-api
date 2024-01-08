package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.StartReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class StartReminderCommandHanlder implements ICommandHandler<Reminder, StartReminderCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public StartReminderCommandHanlder(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(StartReminderCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.start();

        return reminderRepository.save(reminder);
    }
}
