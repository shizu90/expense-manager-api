package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.ChangeReminderMaxRunsCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeReminderMaxRunsCommandHandler implements ICommandHandler<Reminder, ChangeReminderMaxRunsCommand> {
    private final IReminderRepository reminderRepository;

    public ChangeReminderMaxRunsCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(ChangeReminderMaxRunsCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.changeMaxRuns(command.getMaxRuns());

        return reminderRepository.save(reminder);
    }
}
