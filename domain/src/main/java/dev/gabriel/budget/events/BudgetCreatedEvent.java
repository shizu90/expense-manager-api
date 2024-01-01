package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetCreatedEvent extends BudgetEvent {
    public BudgetCreatedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
