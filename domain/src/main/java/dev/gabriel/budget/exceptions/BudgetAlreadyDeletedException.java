package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class BudgetAlreadyDeletedException extends AlreadyDeletedException {
    public BudgetAlreadyDeletedException(String id) {
        super("Bill group " + id + " is already deleted.");
    }
}
