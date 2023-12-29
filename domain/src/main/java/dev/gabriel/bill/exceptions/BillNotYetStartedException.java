package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BillNotYetStartedException extends DomainException {
    public BillNotYetStartedException() {
        super("Bill not yet started.");
    }
}
