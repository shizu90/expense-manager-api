package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class RecurringBillAlreadyDeletedException extends AlreadyDeletedException {
    public RecurringBillAlreadyDeletedException(String id) {
        super("Recurring bill " + id + " is already deleted.");
    }
}
