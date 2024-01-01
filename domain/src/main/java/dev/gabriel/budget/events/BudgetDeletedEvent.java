package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetDeletedEvent extends BudgetEvent {
    public BudgetDeletedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
