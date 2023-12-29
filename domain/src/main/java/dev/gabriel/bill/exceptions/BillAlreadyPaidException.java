package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BillAlreadyPaidException extends DomainException {
    public BillAlreadyPaidException() {
        super("Bill is already marked as paid.");
    }
}
