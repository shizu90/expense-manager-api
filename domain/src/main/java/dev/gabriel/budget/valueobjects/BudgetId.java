package dev.gabriel.budget.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BudgetId extends Identity {
    private BudgetId(String value) {
        super(value);
    }

    public static BudgetId create(String value) {
        return new BudgetId(value);
    }
}
