package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class BillAlreadyDeletedException extends AlreadyDeletedException {
    public BillAlreadyDeletedException(String id) {
        super("Bill " + id + " is already deleted.");
    }
}
