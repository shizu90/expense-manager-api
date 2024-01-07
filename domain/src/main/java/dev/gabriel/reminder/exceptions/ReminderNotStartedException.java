package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class ReminderNotStartedException extends DomainException {
    public ReminderNotStartedException() {
        super("Reminder not started");
    }
}
