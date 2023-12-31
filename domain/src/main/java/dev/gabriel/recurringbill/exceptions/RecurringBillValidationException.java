package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class RecurringBillValidationException extends DataValidationException {
    public RecurringBillValidationException(String field, String message) {
        super("Recurring Bill validation failed on " + field + ": " + message);
    }
}
