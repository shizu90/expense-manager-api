package dev.gabriel.billgroup.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class BillGroupAlreadyDeletedException extends AlreadyDeletedException {
    public BillGroupAlreadyDeletedException(String id) {
        super("Bill group " + id + " is already deleted.");
    }
}
