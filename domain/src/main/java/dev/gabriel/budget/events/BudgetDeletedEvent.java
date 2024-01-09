package dev.gabriel.budget.events;

import java.util.UUID;

public class BudgetDeletedEvent extends BudgetEvent {
    public BudgetDeletedEvent(UUID budgetId, Long version) {
        super(budgetId, version);
    }
}
