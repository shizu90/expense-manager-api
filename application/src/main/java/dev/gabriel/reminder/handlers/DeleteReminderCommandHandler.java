package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.DeleteReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteReminderCommandHandler implements ICommandHandler<Reminder, DeleteReminderCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public DeleteReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(DeleteReminderCommand command) {
        Reminder reminder = reminderRepository
                .load(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.delete();

        reminderRepository.registerEvents(reminder);

        return null;
    }

    @Override
    public Class<DeleteReminderCommand> getCommandType() {
        return DeleteReminderCommand.class;
    }
}
