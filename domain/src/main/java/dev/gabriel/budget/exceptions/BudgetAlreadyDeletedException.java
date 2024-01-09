package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

import java.util.UUID;

public class BudgetAlreadyDeletedException extends AlreadyDeletedException {
    public BudgetAlreadyDeletedException(UUID id) {
        super("Bill group " + id.toString() + " is already deleted.");
    }
}
