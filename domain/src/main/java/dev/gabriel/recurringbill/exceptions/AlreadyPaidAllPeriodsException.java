package dev.gabriel.recurringbill.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class AlreadyPaidAllPeriodsException extends DomainException {
    public AlreadyPaidAllPeriodsException() {
        super("Already paid all periods of recurring bill.");
    }
}
