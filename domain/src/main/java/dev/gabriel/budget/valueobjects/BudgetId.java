package dev.gabriel.budget.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class BudgetId extends Identity {
    private BudgetId(UUID value) {
        super(value);
    }

    public static BudgetId create(UUID value) {
        return new BudgetId(value);
    }
}
