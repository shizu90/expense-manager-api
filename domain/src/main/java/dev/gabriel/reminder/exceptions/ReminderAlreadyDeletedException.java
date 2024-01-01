package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class ReminderAlreadyDeletedException extends DomainException {
    public ReminderAlreadyDeletedException() {
        super("Reminder is already deleted.");
    }
}
