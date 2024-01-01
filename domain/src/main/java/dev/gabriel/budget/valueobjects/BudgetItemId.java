package dev.gabriel.budget.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BudgetItemId extends Identity {
    private BudgetItemId(String value) {
        super(value);
    }

    public static BudgetItemId create(String value) {
        return new BudgetItemId(value);
    }
}
