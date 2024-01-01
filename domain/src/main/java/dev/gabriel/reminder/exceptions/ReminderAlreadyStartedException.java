package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class ReminderAlreadyStartedException extends DomainException {
    public ReminderAlreadyStartedException() {
        super("Reminder already started.");
    }
}
