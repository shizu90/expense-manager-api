package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.ChangeReminderRecurrenceCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeReminderRecurrenceCommandHandler implements ICommandHandler<Reminder, ChangeReminderRecurrenceCommand> {
    private final IReminderRepository reminderRepository;

    public ChangeReminderRecurrenceCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(ChangeReminderRecurrenceCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.changeRecurrence(command.getRecurrence());

        return reminderRepository.save(reminder);
    }
}
