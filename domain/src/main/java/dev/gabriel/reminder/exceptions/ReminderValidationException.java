package dev.gabriel.reminder.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class ReminderValidationException extends DataValidationException {
    public ReminderValidationException(String field, String message) {
        super("Reminder validation failed on " + field + ": " + message);
    }
}
