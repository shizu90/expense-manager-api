package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.RestartReminderCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestartReminderCommandHandler implements ICommandHandler<Reminder, RestartReminderCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public RestartReminderCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(RestartReminderCommand command) {
        Reminder reminder = reminderRepository
                .load(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.restart();

        return reminderRepository.registerEvents(reminder);
    }

    @Override
    public Class<RestartReminderCommand> getCommandType() {
        return RestartReminderCommand.class;
    }
}
