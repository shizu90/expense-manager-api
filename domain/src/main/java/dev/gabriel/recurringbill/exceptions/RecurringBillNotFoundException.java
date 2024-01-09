package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class RecurringBillNotFoundException extends NotFoundException {
    public RecurringBillNotFoundException(UUID id) {
        super("Recurring bill " + id.toString() + " was not found.");
    }
}
