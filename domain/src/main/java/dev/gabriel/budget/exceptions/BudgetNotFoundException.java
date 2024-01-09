package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class BudgetNotFoundException extends NotFoundException {
    public BudgetNotFoundException(UUID budgetId) {
        super("Budget " + budgetId.toString() + " was not found.");
    }
}
