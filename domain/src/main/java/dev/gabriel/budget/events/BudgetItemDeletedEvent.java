package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetItemDeletedEvent extends BudgetEvent {
    public BudgetItemDeletedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
