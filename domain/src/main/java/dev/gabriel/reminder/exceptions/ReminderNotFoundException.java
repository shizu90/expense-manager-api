package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class ReminderNotFoundException extends NotFoundException {
    public ReminderNotFoundException(UUID reminderId) {
        super("Reminder " + reminderId.toString() + " was not found.");
    }
}
