package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class ReminderReachedMaxRunsException extends DomainException {
    public ReminderReachedMaxRunsException() {
        super("Reminder already reached max runs.");
    }
}
