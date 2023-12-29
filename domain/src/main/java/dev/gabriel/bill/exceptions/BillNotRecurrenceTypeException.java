package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BillNotRecurrenceTypeException extends DomainException {
    public BillNotRecurrenceTypeException() {
        super("Bill is not a recurrence type.");
    }
}
