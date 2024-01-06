package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import lombok.Getter;

@Getter
public class BudgetItemsClearedEvent extends BudgetEvent {
    public BudgetItemsClearedEvent(BudgetId budgetId, Long version) {
        super(budgetId, version);
    }
}
