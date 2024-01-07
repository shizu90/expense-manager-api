package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.DomainException;

public class BudgetItemNotPresentException extends DomainException {
    public BudgetItemNotPresentException() {
        super("Bill is not present in budget.");
    }
}
