package dev.gabriel.budget.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class BudgetItemId extends Identity {
    private BudgetItemId(UUID value) {
        super(value);
    }

    public static BudgetItemId create(UUID value) {
        return new BudgetItemId(value);
    }
}
