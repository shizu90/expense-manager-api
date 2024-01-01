package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class ReminderAlreadyStoppedException extends DomainException {
    public ReminderAlreadyStoppedException() {
        super("Reminder already stopped.");
    }
}
