package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetRenamedEvent extends BudgetEvent {
    public BudgetRenamedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
