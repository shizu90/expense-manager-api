package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.ChangeReminderMaxRunsCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeReminderMaxRunsCommandHandler implements ICommandHandler<Reminder, ChangeReminderMaxRunsCommand> {
    private final IReminderRepository reminderRepository;

    @Autowired
    public ChangeReminderMaxRunsCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder handle(ChangeReminderMaxRunsCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.changeMaxRuns(command.getMaxRuns());

        return reminderRepository.save(reminder);
    }

    @Override
    public Class<ChangeReminderMaxRunsCommand> getCommandType() {
        return ChangeReminderMaxRunsCommand.class;
    }
}
