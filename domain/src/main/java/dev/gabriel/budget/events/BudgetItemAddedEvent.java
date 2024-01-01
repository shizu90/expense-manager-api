package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetItemAddedEvent extends BudgetEvent {
    public BudgetItemAddedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
