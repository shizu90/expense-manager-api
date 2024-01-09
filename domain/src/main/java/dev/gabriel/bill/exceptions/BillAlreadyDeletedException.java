package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

import java.util.UUID;

public class BillAlreadyDeletedException extends AlreadyDeletedException {
    public BillAlreadyDeletedException(UUID id) {
        super("Bill " + id + " is already deleted.");
    }
}
