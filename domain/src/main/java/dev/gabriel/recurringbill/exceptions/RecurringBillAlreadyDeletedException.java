package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class RecurringBillAlreadyDeletedException extends AlreadyDeletedException {
    public RecurringBillAlreadyDeletedException() {
        super("Recurring bill is already deleted.");
    }
}
