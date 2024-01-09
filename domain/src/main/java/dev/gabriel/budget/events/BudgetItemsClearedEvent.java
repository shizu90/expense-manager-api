package dev.gabriel.budget.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetItemsClearedEvent extends BudgetEvent {
    public BudgetItemsClearedEvent(UUID budgetId, Long version) {
        super(budgetId, version);
    }
}
