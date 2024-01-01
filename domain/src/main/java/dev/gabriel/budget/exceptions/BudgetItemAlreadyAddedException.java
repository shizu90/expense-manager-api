package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BudgetItemAlreadyAddedException extends DomainException {
    public BudgetItemAlreadyAddedException() {
        super("Bill already added in group.");
    }
}
