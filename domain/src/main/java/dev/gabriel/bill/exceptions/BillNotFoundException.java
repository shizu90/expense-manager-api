package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class BillNotFoundException extends NotFoundException {
    public BillNotFoundException(UUID id) {
        super("Bill " + id.toString() + " was not found.");
    }
}
