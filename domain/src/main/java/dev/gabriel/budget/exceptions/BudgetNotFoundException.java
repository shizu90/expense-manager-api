package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class BudgetNotFoundException extends NotFoundException {
    public BudgetNotFoundException(String budgetId) {
        super("Budget " + budgetId + " was not found.");
    }
}
