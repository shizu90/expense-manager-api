package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.ChangeReminderRecurrenceCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeReminderRecurrenceCommandHandler implements ICommandHandler<Reminder, ChangeReminderRecurrenceCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public ChangeReminderRecurrenceCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(ChangeReminderRecurrenceCommand command) {
        Reminder reminder = reminderRepository
                .load(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.changeRecurrence(command.getRecurrence());

        return reminderRepository.registerEvents(reminder);
    }

    @Override
    public Class<ChangeReminderRecurrenceCommand> getCommandType() {
        return ChangeReminderRecurrenceCommand.class;
    }
}
