package dev.gabriel.budget.events;

public class BudgetDeletedEvent extends BudgetEvent {
    public BudgetDeletedEvent(String budgetId, Long version) {
        super(budgetId, version);
    }
}
