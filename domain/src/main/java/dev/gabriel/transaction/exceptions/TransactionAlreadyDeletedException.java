package dev.gabriel.transaction.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class TransactionAlreadyDeletedException extends AlreadyDeletedException {
    public TransactionAlreadyDeletedException(String id) {
        super("Transaction " + id + " is already deleted.");
    }
}
