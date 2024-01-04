package dev.gabriel.reminder.handlers;

import dev.gabriel.reminder.commands.EditReminderDescriptionCommand;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class EditReminderDescriptionCommandHandler implements ICommandHandler<Reminder, EditReminderDescriptionCommand> {
    private final IReminderRepository reminderRepository;

    public EditReminderDescriptionCommandHandler(IReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Reminder execute(EditReminderDescriptionCommand command) {
        Reminder reminder = reminderRepository
                .findById(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));
        reminder.editDescription(command.getDescription());

        return reminderRepository.save(reminder);
    }
}