package dev.gabriel.billgroup.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BillItemAlreadyAddedException extends DomainException {
    public BillItemAlreadyAddedException() {
        super("Bill already added in group.");
    }
}
