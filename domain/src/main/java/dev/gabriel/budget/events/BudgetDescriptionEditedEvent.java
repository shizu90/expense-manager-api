package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;

public class BudgetDescriptionEditedEvent extends BudgetEvent {
    public BudgetDescriptionEditedEvent(BudgetId budgetId) {
        super(budgetId);
    }
}
