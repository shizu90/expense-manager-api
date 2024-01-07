package dev.gabriel.budget.events;

import lombok.Getter;

@Getter
public class BudgetItemsClearedEvent extends BudgetEvent {
    public BudgetItemsClearedEvent(String budgetId, Long version) {
        super(budgetId, version);
    }
}
