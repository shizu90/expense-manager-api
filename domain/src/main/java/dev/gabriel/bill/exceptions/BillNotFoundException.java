package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class BillNotFoundException extends NotFoundException {
    public BillNotFoundException(String id) {
        super("Bill " + id + " was not found.");
    }
}
