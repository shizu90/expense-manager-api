package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class ReminderNotFoundException extends NotFoundException {
    public ReminderNotFoundException(String reminderId) {
        super("Reminder " + reminderId + " was not found.");
    }
}
