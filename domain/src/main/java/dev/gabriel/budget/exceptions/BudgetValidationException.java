package dev.gabriel.budget.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class BudgetValidationException extends DataValidationException {
    public BudgetValidationException(String field, String message) {
        super("Bill group validation failed on " + field + ": " + message);
    }
}
