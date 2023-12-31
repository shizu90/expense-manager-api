package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.RenameReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameReminderCommandHandler implements ICommandHandler<Reminder, RenameReminderCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public RenameReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(RenameReminderCommand command) {
        Reminder reminder = reminderRepository
                .load(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.rename(command.getName());

        return reminderRepository.registerEvents(reminder);
    }

    @Override
    public Class<RenameReminderCommand> getCommandType() {
        return RenameReminderCommand.class;
    }
}
