package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class RecurringBillNotFoundException extends NotFoundException {
    public RecurringBillNotFoundException(String id) {
        super("Recurring bill " + id + " was not found.");
    }
}
